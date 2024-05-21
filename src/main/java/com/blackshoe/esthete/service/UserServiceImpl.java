package com.blackshoe.esthete.service;

import com.blackshoe.esthete.common.constant.Rule;
import com.blackshoe.esthete.dto.EditUserProfileDto;

import com.blackshoe.esthete.entity.ProfileUrl;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.exception.UserErrorResult;
import com.blackshoe.esthete.exception.UserException;
import com.blackshoe.esthete.repository.ProfileUrlRepository;
import com.blackshoe.esthete.repository.UserRepository;
import com.blackshoe.esthete.util.JwtUtil;
//import com.blackshoe.esthete.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileUrlRepository profileUrlRepository;
    private final JwtUtil jwtUtil;
    //private final S3Util s3Util;

    // 사용자의 프로필 사진을 수정하는 메서드
//    @Override
//    public EditUserProfileDto.EditUserProfileImgResponse editUserProfileImg(String authorizationHeader, MultipartFile multipartFile) {
//        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
//        UUID userId = UUID.fromString(jwtUtil.getUserIdFromToken(accessToken));
//        User user = userRepository.findByUserId(userId).orElseThrow(
//                () -> new UserException(UserErrorResult.NOT_FOUND_USER));
//
//        String key = s3Util.createProfileKey(userId, multipartFile);
//        String s3Url = s3Util.createS3Url(key);
//        String cloudFrontUrl = s3Util.createCloudFrontUrl(key);
//
//        // 사용자 프로필 URL 생성 or 업데이트 및 저장
//        ProfileUrl profileUrl = profileUrlRepository.findByUser(user);
//        // 기존 프로필 사진이 있는 경우
//        if (profileUrl != null) {
//            // 기존 사진 삭제 후 업로드
//            s3Util.deleteS3(profileUrl.getS3Url());
//            s3Util.upload(multipartFile, key);
//            // 프로필 사진 DB 업데이트
//            profileUrl.updateProfileUrl(cloudFrontUrl, s3Url);
//        }
//        // 기존 프로필 사진이 없는 경우
//        else {
//            // 프로필 사진 업로드
//            s3Util.upload(multipartFile, key);
//            // 프로필 사진 DB 생성
//            profileUrl = ProfileUrl.builder()
//                    .user(user)
//                    .profileUrlId(UUID.randomUUID())
//                    .cloudfrontUrl(cloudFrontUrl)
//                    .s3Url(s3Url)
//                    .build();
//        }
//
//        // 새로운 프로필 사진 저장
//        profileUrlRepository.save(profileUrl);
//
//        return EditUserProfileDto.EditUserProfileImgResponse.builder()
//                .s3Url(s3Url)
//                .cloudfrontUrl(cloudFrontUrl)
//                .build();
//    }

    // 사용자의 프로필 정보를 수정하는 메서드
    @Override
    public EditUserProfileDto.EditUserProfileInfosResponse editUserProfileInfos(String authorizationHeader, EditUserProfileDto.EditUserProfileInfosRequest editUserProfileInfosRequest) {
        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        User user = userRepository.findByUserId(UUID.fromString(userId)).orElseThrow(
                () -> new UserException(UserErrorResult.NOT_FOUND_USER));

        // 한 줄 소개 제한 글자 수를 초과한 경우
        if (editUserProfileInfosRequest.getUserIntroduce().length() > Rule.INTRODUCE_LIMIT.getLimit()) {
            throw new UserException(UserErrorResult.INTRODUCE_TOO_LONG);
        }

        // 약력이 제한 글자 수를 초과한 경우
        if (editUserProfileInfosRequest.getUserBiography().length() > Rule.BIOGRAPHY_LIMIT.getLimit()) {
            throw new UserException(UserErrorResult.BIOGRAPHY_TOO_LONG);
        }

        // 사용자 프로필 업데이트 및 저장
        user.updateUserProfile(
                editUserProfileInfosRequest.getUserName(),
                editUserProfileInfosRequest.getUserIntroduce(),
                editUserProfileInfosRequest.getUserBiography()
        );

        userRepository.save(user);

        return EditUserProfileDto.EditUserProfileInfosResponse.builder()
                .userName(user.getNickname())
                .userIntroduce(user.getIntroduce())
                .userBiography(user.getBiography())
                .build();
    }
}