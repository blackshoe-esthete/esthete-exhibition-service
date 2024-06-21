package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.entity.Tag;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.entity.UserTag;
import com.blackshoe.esthete.exception.TagErrorResult;
import com.blackshoe.esthete.exception.TagException;
import com.blackshoe.esthete.repository.*;
import com.blackshoe.esthete.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyGalleryServiceImpl implements MyGalleryService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final PhotoRepository photoRepository;
    private final PhotoUrlRepository photoUrlRepository;
    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionLocationRepository exhibitionLocationRepository;
    private final TemporaryExhibitionRepository temporaryExhibitionRepository;
    private final JwtUtil jwtUtil;

    // 사용자의 태그 목록을 수정하는 메서드
    @Override
    @Transactional
    public EditUserTagsDto.TagList editUserTags(String authorizationHeader, EditUserTagsDto.TagList tagList) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);

        // 중복 태그 확인
        Set<String> tagNames = new HashSet<>();
        for (String tagName : tagList.getTagList()) {
            if (!tagNames.add(tagName)) {
                // 중복된 태그가 있는 경우
                throw new TagException(TagErrorResult.DUPLICATE_TAG);
            }
        }

        // 기존 태그들 제거
        userTagRepository.deleteAllByUser(user);

        // 새로운 태그들을 생성하여 UserTag 엔티티로 변환하여 저장
        List<UserTag> newTagNameList = tagList.getTagList().stream()
                .map(tagName -> {
                    Tag tag = tagRepository.findByName(tagName)
                            .orElseThrow(() -> new TagException(TagErrorResult.NOT_FOUND_TAG));
                    return UserTag.builder()
                            .user(user)
                            .tag(tag)
                            .build();
                })
                .collect(Collectors.toList());

        // 새로 생성한 UserTag 엔티티들을 저장
        userTagRepository.saveAll(newTagNameList);

        // 응답에 유저의 태그 리스트를 포함하여 반환
        return tagList;
    }

//    // 전시를 업로드하는 메소드
//    @Override
//    @Transactional
//    public UploadExhibitionDto.UploadExhibitionResponse uploadExhibition(String authorizationHeader, UploadExhibitionDto.UploadExhibitionRequest uploadExhibitionRequest) {
//        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
//        String userId = jwtUtil.getUserIdFromToken(accessToken);
//        User user = userRepository.findByUserId(UUID.fromString(userId)).orElseThrow(
//                () -> new UserException(UserErrorResult.NOT_FOUND_USER));
//        TemporaryExhibition temporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(uploadExhibitionRequest.getTemporaryExhibitionId()).orElseThrow(
//                () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));
//
//        // Exhibition 엔터티 생성 & 저장
//        Exhibition exhibition = Exhibition.builder()
//                .user(user)
//                .exhibitionId(temporaryExhibition.getTemporaryExhibitionId())
//                .cloudfrontUrl(temporaryExhibition.getCloudfrontUrl())
//                .title(uploadExhibitionRequest.getTitle())
//                .description(uploadExhibitionRequest.getDescription())
//                .build();
//
//        exhibitionRepository.save(exhibition);
//
//        // ExhibitionTag 엔터티 생성 & 저장
//        for (String tagName : uploadExhibitionRequest.getTagList()) {
//            Tag tag = tagRepository.findByName(tagName).orElseThrow(
//                    () -> new TagException(TagErrorResult.NOT_FOUND_TAG));
//
//            ExhibitionTag exhibitionTag = ExhibitionTag.builder()
//                    .user(user)
//                    .exhibition(exhibition)
//                    .tag(tag)
//                    .build();
//
//            exhibitionTagRepository.save(exhibitionTag);
//        }
//
//        // Photo & PhotoUrl 엔터티 생성 & 저장
//        List<TemporaryExhibitionPhoto> temporaryExhibitionPhotoList = temporaryExhibitionPhotoRepository.findAllByTemporaryExhibition(temporaryExhibition).orElseThrow(
//                () -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO)
//        );
//        for (TemporaryExhibitionPhoto temporaryExhibitionPhoto : temporaryExhibitionPhotoList) {
//            Photo photo = Photo.builder()
//                    .user(user)
//                    .exhibition(exhibition)
//                    .build();
//
//            photoRepository.save(photo);
//
//            PhotoUrl photoUrl = PhotoUrl.builder()
//                    .photo(photo)
//                    .cloudfrontUrl(temporaryExhibition.getCloudfrontUrl())
//                    .s3Url(temporaryExhibitionPhoto.getS3Url())
//                    .build();
//
//            photoUrlRepository.save(photoUrl);
//        }
//
//        // ExhibitionLocation 엔터티 생성 & 저장
//        if (uploadExhibitionRequest.getLocationInfo() != null) {
//            ExhibitionLocation exhibitionLocation = ExhibitionLocation.builder()
//                    .user(user)
//                    .exhibition(exhibition)
//                    .longitude(uploadExhibitionRequest.getLocationInfo().getLongitude())
//                    .latitude(uploadExhibitionRequest.getLocationInfo().getLatitude())
//                    .state(uploadExhibitionRequest.getLocationInfo().getState())
//                    .city(uploadExhibitionRequest.getLocationInfo().getCity())
//                    .town(uploadExhibitionRequest.getLocationInfo().getTown())
//                    .build();
//
//            exhibitionLocationRepository.save(exhibitionLocation);
//        }
//
//        // TemporaryExhibition 엔터티 삭제
//        temporaryExhibitionRepository.delete(temporaryExhibition);
//
//        return UploadExhibitionDto.UploadExhibitionResponse.builder()
//                .exhibitionId(exhibition.getExhibitionId())
//                .build();
//    }
}