package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.CreateExhibitionDto;
import com.blackshoe.esthete.entity.*;
import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import com.blackshoe.esthete.exception.UserErrorResult;
import com.blackshoe.esthete.exception.UserException;
import com.blackshoe.esthete.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdditionServiceImpl implements AdditionService{

    private final TemporaryExhibitionRepository temporaryExhibitionRepository;
    private final PhotoRepository photoRepository;
    private final PhotoUrlRepository photoUrlRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final TagRepository tagRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionLocationRepository exhibitionLocationRepository;
    private final UserRepository userRepository;
    private final S3Client amazonS3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String BUCKET;
    @Value("${spring.cloud.aws.cloudfront.domain-name}")
    private String CLOUDFRONT_DOMAIN;
    @Value("${spring.cloud.aws.s3.exhibition-photo-directory}")
    private String EXHIBITION_PHOTO_DIRECTORY;
    @Value("${spring.cloud.aws.s3.root-directory}")
    private String ROOT_DIRECTORY;

    @Override
    @Transactional //임시저장
    public CreateExhibitionDto.CreateTmpExhibitionResponse saveTemporaryExhibition(UUID userId, List<MultipartFile> exhibitionPhotos, CreateExhibitionDto.CreateExhibitionRequest requestDto) {
        if (requestDto.getTmpExhibitionId() != null) { // 여러번 임시저장 -> 업데이트
            log.info("여러번 임시저장");

            TemporaryExhibition findTemporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(requestDto.getTmpExhibitionId()).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));

            //전시 사진들과 필터 및 설정값 변경
            List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrls = uploadExhibitionPhotoWithFilter(exhibitionPhotos, findTemporaryExhibition.getTemporaryExhibitionId(), null);
            saveExhibitionPhoto(exhibitionPhotoImgUrls, requestDto, findTemporaryExhibition.getTemporaryExhibitionId(), null);

            //전시 정보 업데이트
            saveExhibitionPhotoInformation(findTemporaryExhibition.getTemporaryExhibitionId(), requestDto, null);

            return CreateExhibitionDto.CreateTmpExhibitionResponse.builder()
                    .tmpExhibitionId(findTemporaryExhibition.getTemporaryExhibitionId())
                    .createdAt(findTemporaryExhibition.getCreatedAt())
                    .build();
        }else{
            log.info("첫 임시저장");
            User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
            TemporaryExhibition temporaryExhibition = TemporaryExhibition.builder().build();
            temporaryExhibition.setUser(user);
            temporaryExhibitionRepository.save(temporaryExhibition);// @PrePersist로 인한 널값 방지 -> 나중에 리팩토링

            //전시 사진들과 필터 및 설정값 생성
            List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrls = uploadExhibitionPhotoWithFilter(exhibitionPhotos, temporaryExhibition.getTemporaryExhibitionId(), null);
            saveExhibitionPhoto(exhibitionPhotoImgUrls, requestDto, temporaryExhibition.getTemporaryExhibitionId(), null);
            log.info("1. 전시 사진들과 필터 및 설정값 생성");

            //전시 정보 생성
            saveExhibitionPhotoInformation(temporaryExhibition.getTemporaryExhibitionId(), requestDto, null);
            log.info("2. 전시 정보 생성");

            return CreateExhibitionDto.CreateTmpExhibitionResponse.builder()
                    .tmpExhibitionId(temporaryExhibition.getTemporaryExhibitionId())
                    .createdAt(temporaryExhibition.getCreatedAt())
                    .build();

        }
    }

    @Override
    @Transactional
    public CreateExhibitionDto.CreateExhibitionResponse saveExhibition(UUID userId, List<MultipartFile> exhibitionPhotos, CreateExhibitionDto.CreateExhibitionRequest requestDto){
        Optional<TemporaryExhibition> findTemporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(requestDto.getTmpExhibitionId());

        if (findTemporaryExhibition.isPresent()) { // 임시저장 테이블에 해당 전시가 있는 경우 -> 업데이트 후 삭제하고 전시 테이블로 옮김
            log.info("임시저장 이력이 있는 저장");

            //전시 사진들과 필터 및 설정값 변경
            List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrls = uploadExhibitionPhotoWithFilter(exhibitionPhotos, findTemporaryExhibition.get().getTemporaryExhibitionId(), null);
            saveExhibitionPhoto(exhibitionPhotoImgUrls, requestDto, findTemporaryExhibition.get().getTemporaryExhibitionId(), null);

            //전시 정보 업데이트
            saveExhibitionPhotoInformation(findTemporaryExhibition.get().getTemporaryExhibitionId(), requestDto, null);

            //임시저장 데이터 삭제 후 전시 테이블로 이전
            Exhibition exhibition = transferTmpExhibitionToExhibition(findTemporaryExhibition.get());

            return CreateExhibitionDto.CreateExhibitionResponse.builder()
                    .exhibitionId(exhibition.getExhibitionId())
                    .createdAt(exhibition.getCreatedAt())
                    .build();
        }else{
            log.info("바로 전시 저장");
            User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
            Exhibition exhibition = Exhibition.builder().build();
            exhibition.setUser(user);
            exhibitionRepository.save(exhibition);// @PrePersist로 인한 널값 방지 -> 나중에 리팩토링

            //전시 사진들과 필터 및 설정값 생성
            List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrls = uploadExhibitionPhotoWithFilter(exhibitionPhotos, null, exhibition.getExhibitionId());
            saveExhibitionPhoto(exhibitionPhotoImgUrls, requestDto, null, exhibition.getExhibitionId());
            log.info("1. 전시 사진들과 필터 및 설정값 생성");

            //전시 정보 생성
            saveExhibitionPhotoInformation(null, requestDto, exhibition.getExhibitionId());
            log.info("2. 전시 정보 생성");

            return CreateExhibitionDto.CreateExhibitionResponse.builder()
                    .exhibitionId(exhibition.getExhibitionId())
                    .createdAt(exhibition.getCreatedAt())
                    .build();

        }
    }


    public List<CreateExhibitionDto.ExhibitionPhotoImgUrl> uploadExhibitionPhotoWithFilter(List<MultipartFile> exhibitionPhotos, UUID temporaryExhibitionId, UUID exhibitionId){
        if(temporaryExhibitionId != null && exhibitionId == null){ // 임시저장 전시 경우, 설정값 업데이트
            TemporaryExhibition findTemporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(temporaryExhibitionId).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));

            if(photoRepository.existsAllByTemporaryExhibition(findTemporaryExhibition)){
                List<Photo> findAllTmpExhibitionPhoto = photoRepository.findAllByTemporaryExhibition(findTemporaryExhibition).orElseThrow(
                        () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO)
                );
                log.info("두번째 임시저장 경우, 임시저장 전시 사진 s3, db 삭제");

                for(Photo temporaryExhibitionPhoto : findAllTmpExhibitionPhoto){
                    PhotoUrl photoUrl = photoUrlRepository.findByPhoto(temporaryExhibitionPhoto).orElseThrow(
                            () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_PHOTO_URL));

                    String tmpExhibitionPhotoS3Url = photoUrl.getS3Url();

                    String key = tmpExhibitionPhotoS3Url.substring(tmpExhibitionPhotoS3Url.indexOf(ROOT_DIRECTORY));

                    try {
                        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                                .bucket(BUCKET)
                                .key(key)
                                .build();
                        amazonS3Client.deleteObject(deleteObjectRequest);
                        log.info("전시 사진 삭제중");
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO);
                    }
                }
            }
        }

        log.info("임시저장 사진 업로드 시작");
        List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrlDtos = new ArrayList<>();

        String s3FilePath;
        if(temporaryExhibitionId != null && exhibitionId == null){
            s3FilePath = temporaryExhibitionId + "/" + EXHIBITION_PHOTO_DIRECTORY;
        }else if(exhibitionId != null && temporaryExhibitionId == null){
            s3FilePath = exhibitionId + "/" + EXHIBITION_PHOTO_DIRECTORY;
        }else{
            s3FilePath = null;
        }

        //사진 URL 생성 -> 아직 db 저장x

//        int idx = 0;
        for(MultipartFile exhibitionPhoto : exhibitionPhotos) {
            CreateExhibitionDto.ExhibitionPhotoImgUrl exhibitionPhotoImgUrlDto;

            if(exhibitionPhoto == null || exhibitionPhoto.isEmpty()){
                exhibitionPhotoImgUrlDto = CreateExhibitionDto.ExhibitionPhotoImgUrl.builder()
                        .cloudfrontUrl("")
                        .s3Url("")
                        .build();

                exhibitionPhotoImgUrlDtos.add(exhibitionPhotoImgUrlDto);
                continue;
            }

            String fileExtension = exhibitionPhoto.getOriginalFilename().substring(exhibitionPhoto.getOriginalFilename().lastIndexOf("."));
            String key = ROOT_DIRECTORY + "/" + s3FilePath + "/" + UUID.randomUUID() + fileExtension;

            if(exhibitionPhoto.getSize() > 52428800){
                throw new ExhibitionException(ExhibitionErrorResult.INVALID_EXHIBITION_PHOTO_IMG_SIZE);
            }

            try {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(BUCKET)
                        .key(key)
                        .contentType(exhibitionPhoto.getContentType()) // url로 접근했을때 view가 가능하도록 함, not download
                        .build();

                amazonS3Client.putObject(putObjectRequest, RequestBody.fromInputStream(exhibitionPhoto.getInputStream(), exhibitionPhoto.getSize()));
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new ExhibitionException(ExhibitionErrorResult.EXHIBITION_PHOTO_IMG_UPLOAD_FAILED);
            }

            String s3Url = "https://" + BUCKET + ".s3.amazonaws.com/" + key;
            String cloudFrontUrl = "https://" + CLOUDFRONT_DOMAIN + "/" + key;

            exhibitionPhotoImgUrlDto = CreateExhibitionDto.ExhibitionPhotoImgUrl.builder()
                    .s3Url(s3Url)
                    .cloudfrontUrl(cloudFrontUrl)
                    .build();

            exhibitionPhotoImgUrlDtos.add(exhibitionPhotoImgUrlDto);
        }
        return exhibitionPhotoImgUrlDtos;
    }

    public void saveExhibitionPhoto(List<CreateExhibitionDto.ExhibitionPhotoImgUrl> exhibitionPhotoImgUrls, CreateExhibitionDto.CreateExhibitionRequest requestDto, UUID temporaryExhibitionId, UUID exhibitionId){
        if(temporaryExhibitionId != null && exhibitionId == null){ // 임시저장 경우
            TemporaryExhibition findTemporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(temporaryExhibitionId).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));

            if(photoRepository.existsAllByTemporaryExhibition(findTemporaryExhibition)) { // 두번째 임시저장 -> update
                log.info("두번째 임시저장 경우, 전시 사진 url주소 삭제");
                List<Photo> findAllTemporaryExhibitionPhoto = photoRepository.findAllByTemporaryExhibition(findTemporaryExhibition).orElseThrow(
                        () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO));

                for(Photo temporaryExhibitionPhoto : findAllTemporaryExhibitionPhoto){
                    PhotoUrl photoUrl = photoUrlRepository.findByPhoto(temporaryExhibitionPhoto).orElseThrow(
                            () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_PHOTO_URL));
                    photoUrlRepository.delete(photoUrl);
                }

                photoRepository.deleteAll(findAllTemporaryExhibitionPhoto);
            }

            log.info("전시사진 url주소 생성 중");
            int idx=0;
            for(CreateExhibitionDto.FilterPhoto filterPhoto : requestDto.getFilterPhotoList().getFilterPhotos()) {
                log.info("전시사진 url주소 생성 중 : " + idx);
                Photo temporaryExhibitionPhoto = Photo.builder() // 전시 사진 객체 생성
                    .grayScale(filterPhoto.getGrayScale())
                    .filterId(filterPhoto.getFilterId())
                    .build();

                PhotoUrl temporaryExhibitionPhotoUrl = PhotoUrl.builder()
                        .s3Url(exhibitionPhotoImgUrls.get(idx).getS3Url())
                        .cloudfrontUrl(exhibitionPhotoImgUrls.get(idx).getCloudfrontUrl())
                        .build();

                temporaryExhibitionPhotoUrl.setPhoto(temporaryExhibitionPhoto);
                temporaryExhibitionPhoto.setTemporaryExhibition(findTemporaryExhibition);
                photoRepository.save(temporaryExhibitionPhoto);
                photoUrlRepository.save(temporaryExhibitionPhotoUrl);
                idx++;
            }
            //exhibition에 썸네일 주소 넣기
            findTemporaryExhibition.setCloudfrontUrl(exhibitionPhotoImgUrls.get(0).getCloudfrontUrl());

        }else if(exhibitionId != null && temporaryExhibitionId == null){
            log.info("바로 저장 경우, 대표사진 url db에 생성");
            Exhibition findExhibition = exhibitionRepository.findByExhibitionId(exhibitionId).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));

            int idx=0;
            for(CreateExhibitionDto.FilterPhoto filterPhoto : requestDto.getFilterPhotoList().getFilterPhotos()) {

                Photo exhibitionPhoto = Photo.builder() // 전시 사진 객체 생성
                        .grayScale(filterPhoto.getGrayScale())
                        .filterId(filterPhoto.getFilterId())
                        .build();

                PhotoUrl exhibitionPhotoUrl = PhotoUrl.builder()
                        .s3Url(exhibitionPhotoImgUrls.get(idx).getS3Url())
                        .cloudfrontUrl(exhibitionPhotoImgUrls.get(idx).getCloudfrontUrl())
                        .build();

                exhibitionPhotoUrl.setPhoto(exhibitionPhoto);
                exhibitionPhoto.setExhibition(findExhibition);
                photoRepository.save(exhibitionPhoto);
                photoUrlRepository.save(exhibitionPhotoUrl);
                idx++;
            }
            //exhibition에 썸네일 주소 넣기
            findExhibition.setCloudfrontUrl(exhibitionPhotoImgUrls.get(0).getCloudfrontUrl());
        }
    }

    public void saveExhibitionPhotoInformation(UUID temporaryExhibitionId, CreateExhibitionDto.CreateExhibitionRequest requestDto, UUID exhibitionId){
        if(temporaryExhibitionId != null && exhibitionId == null){ //임시저장
            TemporaryExhibition findTemporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(temporaryExhibitionId).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));

            //기존 전시 태그 삭제 -> 두번째 임시서장
            if(exhibitionTagRepository.existsAllByTemporaryExhibition(findTemporaryExhibition)) {
                log.info("두번째 임시저장 경우, 전시태그 삭제");
                List<ExhibitionTag> exhibitionTags = exhibitionTagRepository.findAllByTemporaryExhibition(findTemporaryExhibition).orElseThrow(
                        () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_TAG));
                exhibitionTagRepository.deleteAll(exhibitionTags);
            }

            // 전시 태그 추가(변경) -> 첫, 둘 임시저장
            for(UUID tag : requestDto.getExhibitionInformation().getTagList().getTags()){
                log.info("tag : " + tag);
                ExhibitionTag exhibitionTag = ExhibitionTag.builder().build();
                exhibitionTag.updateTemporaryExhibition(findTemporaryExhibition);

                Tag savedtag = tagRepository.findByTagId(tag).orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TAG));

                exhibitionTag.updateTag(savedtag);
                exhibitionTagRepository.save(exhibitionTag);
            }

            // 전시 정보 수정 -> 첫, 둘 임시저장
            findTemporaryExhibition.updateTemporaryExhibitionInfo(requestDto.getExhibitionInformation().getTitle(), requestDto.getExhibitionInformation().getDescription());

            // 전시 위치 수정
            if(exhibitionLocationRepository.existsByTemporaryExhibition(findTemporaryExhibition)){// 기존 임시저장 있는 경우
                ExhibitionLocation findExhibitionLocation = exhibitionLocationRepository.findByTemporaryExhibition(findTemporaryExhibition).orElseThrow(
                        () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION_LOCATION));

                findExhibitionLocation.changeExhibitionLocation(
                        requestDto.getExhibitionLocation().getLongitude(),
                        requestDto.getExhibitionLocation().getLatitude(),
                        requestDto.getExhibitionLocation().getState(),
                        requestDto.getExhibitionLocation().getCity(),
                        requestDto.getExhibitionLocation().getTown());

                exhibitionLocationRepository.save(findExhibitionLocation);
            }else{ // 첫 임시저장
                ExhibitionLocation exhibitionLocation = ExhibitionLocation.builder()
                        .longitude(requestDto.getExhibitionLocation().getLongitude())
                        .latitude(requestDto.getExhibitionLocation().getLatitude())
                        .state(requestDto.getExhibitionLocation().getState())
                        .city(requestDto.getExhibitionLocation().getCity())
                        .town(requestDto.getExhibitionLocation().getTown())
                        .build();

                exhibitionLocation.updateTemporaryExhibition(findTemporaryExhibition);
                exhibitionLocationRepository.save(exhibitionLocation);
            }

            temporaryExhibitionRepository.save(findTemporaryExhibition);

        }else if (exhibitionId != null && temporaryExhibitionId == null) { //바로 저장
            Exhibition findExhibition = exhibitionRepository.findByExhibitionId(exhibitionId).orElseThrow(
                    () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));
            log.info("바로 저장 경우, 필터태그 생성");
            for(UUID tag : requestDto.getExhibitionInformation().getTagList().getTags()){
                ExhibitionTag exhibitionTag = ExhibitionTag.builder().build();
                exhibitionTag.updateExhibition(findExhibition);

                Tag savedtag = tagRepository.findByTagId(tag).orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TAG));
                exhibitionTag.updateTag(savedtag);
            }
            findExhibition.updateExhibitionInfo(requestDto.getExhibitionInformation().getTitle(), requestDto.getExhibitionInformation().getDescription());

            ExhibitionLocation exhibitionLocation = ExhibitionLocation.builder()
                    .longitude(requestDto.getExhibitionLocation().getLongitude())
                    .latitude(requestDto.getExhibitionLocation().getLatitude())
                    .state(requestDto.getExhibitionLocation().getState())
                    .city(requestDto.getExhibitionLocation().getCity())
                    .town(requestDto.getExhibitionLocation().getTown())
                    .build();

            exhibitionLocation.updateExhibition(findExhibition);
            exhibitionLocationRepository.save(exhibitionLocation);
        }

    }

    public Exhibition transferTmpExhibitionToExhibition(TemporaryExhibition temporaryExhibition){
        User user = userRepository.findByUserId(temporaryExhibition.getUser().getUserId()).orElseThrow(
                () -> new UserException(UserErrorResult.NOT_FOUND_USER));

        Exhibition exhibition = Exhibition.builder().build();
        exhibition.setUser(user);
        exhibitionRepository.save(exhibition);

        //thumbnail
        exhibition.setCloudfrontUrl(temporaryExhibition.getThumbnailUrl());

        //photo
        List<Photo> photos = photoRepository.findAllByTemporaryExhibition(temporaryExhibition).orElseThrow(
                () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO));

        for(Photo photo : photos){
            photo.setExhibition(exhibition);
        }

        //exhibitionInfo
        exhibition.updateExhibitionInfo(temporaryExhibition.getTitle(), temporaryExhibition.getDescription());

        //exhibitionTag
        List<ExhibitionTag> exhibitionTags = exhibitionTagRepository.findAllByTemporaryExhibition(temporaryExhibition).orElseThrow(
                () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_TAG));

        for(ExhibitionTag exhibitionTag : exhibitionTags){
            exhibitionTag.updateExhibition(exhibition);
        }

        //exhibitionLocation
        ExhibitionLocation exhibitionLocation = exhibitionLocationRepository.findByTemporaryExhibition(temporaryExhibition).orElseThrow(
                () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION_LOCATION));

        exhibitionLocation.updateExhibition(exhibition);

        exhibitionRepository.save(exhibition);

        log.info("임시저장 전시 옮기기, 자식들과 연관관계 끊기 -> 데이터 삭제");
        //임시저장 전시 자식들과 연관관계 끊기 -> 데이터 삭제

        //thumbnail 삭제 -> 추가함
        temporaryExhibition.setCloudfrontUrl(null);

        for(Photo photo : photos){
            photo.deleteTemporaryExhibition(temporaryExhibition);
        }

        for(ExhibitionTag exhibitionTag : exhibitionTags){
            exhibitionTag.deleteTemporaryExhibition();
        }

        exhibitionLocation.deleteTemporaryExhibition();

        temporaryExhibition.deleteUser();

        temporaryExhibitionRepository.delete(temporaryExhibition);

        return exhibition;

    }
}