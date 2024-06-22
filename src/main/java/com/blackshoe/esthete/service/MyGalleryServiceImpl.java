package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.MyGalleryDto;
import com.blackshoe.esthete.entity.*;
import com.blackshoe.esthete.exception.*;
import com.blackshoe.esthete.repository.*;
import com.blackshoe.esthete.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyGalleryServiceImpl implements MyGalleryService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final TemporaryExhibitionRepository temporaryExhibitionRepository;
    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;
    private final JwtUtil jwtUtil;

    // 사용자 태그 목록 수정 메서드
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

    // 임시 저장 전시회 전체 조회 메서드
    @Override
    public List<MyGalleryDto.TemporaryExhibitionResponse> getTemporaryExhibitions(String authorizationHeader) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        List<TemporaryExhibition> temporaryExhibitions = temporaryExhibitionRepository.findAllByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new MyGalleryException(MyGalleryErrorResult.FAIL_TO_GET_TEMPORARY_EXHIBITIONS));
        return MyGalleryDto.TemporaryExhibitionResponse.of(temporaryExhibitions);
    }

    // 임시 저장 전시회 상세 조회 메서드
    @Override
    public MyGalleryDto.TemporaryExhibitionDetailResponse getTemporaryExhibitionDetails(String authorizationHeader, String tempExhibitionId) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        TemporaryExhibition temporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(UUID.fromString(tempExhibitionId))
                .orElseThrow(() -> new MyGalleryException(MyGalleryErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));

        return MyGalleryDto.TemporaryExhibitionDetailResponse.of(temporaryExhibition);
    }

    // 임시 저장 전시회 삭제 메서드
    @Override
    public void removeTemporaryExhibition(String authorizationHeader, String tempExhibitionId) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        TemporaryExhibition temporaryExhibition = temporaryExhibitionRepository.findByTemporaryExhibitionId(UUID.fromString(tempExhibitionId))
                .orElseThrow(() -> new MyGalleryException(MyGalleryErrorResult.NOT_FOUND_TEMPORARY_EXHIBITION));
        temporaryExhibitionRepository.delete(temporaryExhibition);
    }

    // 작가 소개 조회 메서드
    @Override
    public MyGalleryDto.AuthorIntroductionResponse getAuthorDetails(String authorizationHeader, String userId) {
        String userType = determineUserType(authorizationHeader, userId);
        User user = switch (userType) {
            case "OWNER" -> jwtUtil.getUserFromHeader(authorizationHeader);
            case "OTHER", "GUEST" -> userRepository.findByUserId(UUID.fromString(userId))
                    .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
            default -> throw new MyGalleryException(MyGalleryErrorResult.BAD_REQUEST);
        };

        MyGalleryDto.AuthorIntroductionResponse authorIntroductionResponse = MyGalleryDto.AuthorIntroductionResponse.of(user);
        // 타인인 경우 팔로우 여부 확인
        if (userType.equals("OTHER")) {
            User ownUser = jwtUtil.getUserFromHeader(authorizationHeader);
            boolean isFollowed = followRepository.existsByUserAndFollowerId(user, ownUser.getUserId());
            authorIntroductionResponse.updateFollow(isFollowed);
        }
        return authorIntroductionResponse;
    }

    // 전시를 전체 조회하는 메서드
    @Override
    public List<MyGalleryDto.ExhibitionResponse> getAllExhibitions(String authorizationHeader, String userId) {
        String userType = determineUserType(authorizationHeader, userId);
        User user;
        List<Like> likes = null;

        switch (userType) {
            case "OWNER":
                user = jwtUtil.getUserFromHeader(authorizationHeader);
                break;
            case "OTHER":
            case "GUEST":
                user = userRepository.findByUserId(UUID.fromString(userId))
                        .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
                likes = likeRepository.findAllByUserId(UUID.fromString(userId));
                break;
            default:
                throw new MyGalleryException(MyGalleryErrorResult.BAD_REQUEST);
        }

        List<Exhibition> exhibitions = exhibitionRepository.findAllByUser(user)
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITIONS));
        return MyGalleryDto.ExhibitionResponse.of(exhibitions, likes);
    }

    // 좋아요 전시를 조회하는 메서드
    @Override
    public List<MyGalleryDto.LikeExhibitionResponse> getLikeExhibitions(String authorizationHeader) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        List<Like> likes = likeRepository.findAllByUserId(user.getUserId());
        List<Exhibition> exhibitions = likes.stream()
                .map(like -> exhibitionRepository.findByExhibitionId(like.getExhibitionId())
                        .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION)))
                .toList();
        return MyGalleryDto.LikeExhibitionResponse.of(exhibitions);
    }

    // 유저 타입을 결정하는 메서드
    private String determineUserType(String authorizationHeader, String userId) {
        if (!Objects.isNull(authorizationHeader) && !Objects.isNull(userId)) {
            return "OTHER"; // 회원이 타인의 프로필 조회
        } else if (!Objects.isNull(authorizationHeader)) {
            return "OWNER"; // 회원이 자신의 프로필 조회
        } else if (!Objects.isNull(userId)) {
            return "GUEST"; // 비회원이 타인의 프로필 조회
        } else {
            throw new MyGalleryException(MyGalleryErrorResult.BAD_REQUEST);
        }
    }
}