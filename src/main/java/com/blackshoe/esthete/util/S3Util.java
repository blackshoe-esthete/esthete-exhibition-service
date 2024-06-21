package com.blackshoe.esthete.util;

import com.blackshoe.esthete.exception.S3ErrorResult;
import com.blackshoe.esthete.exception.S3Exception;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Util {
    private final S3Operations s3Operations;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String BUCKET;
    @Value("${spring.cloud.aws.cloudfront.domain-name}")
    private String CLOUDFRONT_DOMAIN;
    @Value("${spring.cloud.aws.s3.user-directory}")
    private String USER_DIRECTORY;
    @Value("${spring.cloud.aws.s3.profile-directory}")
    private String PROFILE_DIRECTORY;
    @Value("${spring.cloud.aws.s3.exhibition-photo-directory}")
    private String EXHIBITION_PHOTO_DIRECTORY; // 환경변수 추가ㅎ

    // S3에 파일을 업로드하는 메서드
    public void upload(MultipartFile multipartFile, String key) { // dirName의 디렉토리가 S3 Bucket 내부에 생성됨
        // S3에 업로드
        putS3(multipartFile, key);
    }

    // S3에 파일을 추가하는 메서드
    private void putS3(MultipartFile multipartFile, String key) {
        try {
            s3Operations.upload(BUCKET, key, multipartFile.getInputStream(), ObjectMetadata.builder().contentType(multipartFile.getContentType()).build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new S3Exception(S3ErrorResult.S3_UPLOAD_FAILED);
        }
    }

    // S3에서 파일을 삭제하는 메서드
    public void deleteS3(String s3Url) throws S3Exception {
        try {
            String key = extractKeyFromS3Url(s3Url);
            s3Operations.deleteObject(BUCKET, key);
            log.info("S3 파일이 삭제되었습니다.");
        } catch (Exception e) {
            throw new S3Exception(S3ErrorResult.S3_DELETE_FAILED);
        }
    }

    // S3 URL에서 키 추출
    public String extractKeyFromS3Url(String s3Url) {
        String[] parts = s3Url.split("/");
        // 슬래시 3개 이후의 모든 부분을 합쳐서 키로 반환
        return String.join("/", Arrays.copyOfRange(parts, 3, parts.length));
    }

    // 로컬에 있는 File을 제거하는 메서드
    private void removeLocalFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    // MultipartFile -> File 변환
    private File convert(MultipartFile multipartFile) throws S3Exception {
        // 이미지 크기 확인
        if (multipartFile.getSize() > 52428800) {
            throw new S3Exception(S3ErrorResult.IMAGE_SIZE_TOO_LARGE); // 이미지 크기가 너무 큰 경우
        }

        File convertedFile;
        try {
            convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            multipartFile.transferTo(convertedFile);
        } catch (Exception e) {
            throw new S3Exception(S3ErrorResult.CONVERSION_FAILED);
        }

        return convertedFile;
    }

    // 프로필 키 생성
    public String createProfileKey(UUID userId, MultipartFile multipartFile) {
        String fileExtension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        return USER_DIRECTORY + "/" + userId + "/" + PROFILE_DIRECTORY + "/" + UUID.randomUUID() + fileExtension;
    }

    public String createExhibitionPhotoKey(UUID userId, MultipartFile multipartFile) {
        String fileExtension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        return USER_DIRECTORY + "/" + userId + "/" + EXHIBITION_PHOTO_DIRECTORY + "/" + UUID.randomUUID() + fileExtension;
    }

    // S3 URL 생성
    public String createS3Url(String key) {
        return "https://" + BUCKET + ".s3.amazonaws.com/" + key;
    }

    // CloudFront URL 생성
    public String createCloudFrontUrl(String key) {
        return CLOUDFRONT_DOMAIN + "/" + key;
    }
}