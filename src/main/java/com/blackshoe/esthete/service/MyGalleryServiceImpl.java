package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.entity.Tag;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.entity.UserTag;
import com.blackshoe.esthete.exception.TagErrorResult;
import com.blackshoe.esthete.exception.TagException;
import com.blackshoe.esthete.exception.UserErrorResult;
import com.blackshoe.esthete.exception.UserException;
import com.blackshoe.esthete.repository.TagRepository;
import com.blackshoe.esthete.repository.UserRepository;
import com.blackshoe.esthete.repository.UserTagRepository;
import com.blackshoe.esthete.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyGalleryServiceImpl implements MyGalleryService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;
    private final JwtUtil jwtUtil;

    // 사용자의 태그 목록을 수정하는 메서드
    @Override
    @Transactional
    public EditUserTagsDto.EditUserTagsResponse editUserTags(String authorizationHeader, EditUserTagsDto.EditUserTagsRequest editUserTagsRequest) {
        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        String userId = jwtUtil.getUserIdFromToken(accessToken);
        User user = userRepository.findByUserId(UUID.fromString(userId)).orElseThrow(
                () -> new UserException(UserErrorResult.NOT_FOUND_USER));

        List<EditUserTagsDto.TagName> tagNameList = editUserTagsRequest.getTagNameList();

        // 중복 태그 확인
        Set<String> tagNames = new HashSet<>();
        for (EditUserTagsDto.TagName tagName : tagNameList) {
            if (!tagNames.add(tagName.getTagName())) {
                // 중복된 태그가 있는 경우
                throw new TagException(TagErrorResult.DUPLICATE_TAG);
            }
        }

        // 기존 태그들 제거
        userTagRepository.deleteAllByUser(user);

        // 새로운 태그들을 생성하여 UserTag 엔티티로 변환하여 저장
        List<UserTag> newTagNameList = tagNameList.stream()
                .map(tagName -> {
                    Tag tag = tagRepository.findByName(tagName.getTagName())
                            .orElseThrow(() -> new TagException(TagErrorResult.NOT_FOUND_TAG));
                    return UserTag.builder()
                            .user(user)
                            .tag(tag)
                            .build();
                })
                .collect(Collectors.toList());

        // 새로 생성한 UserTag 엔티티들을 저장
        userTagRepository.saveAll(newTagNameList);

        // 유저의 태그 리스트 조회
        List<EditUserTagsDto.TagName> savedTagNameList = userTagRepository.findByUser(user).stream()
                .map(userTag -> EditUserTagsDto.TagName.builder().tagName(userTag.getTag().getName()).build())
                .collect(Collectors.toList());

        // 응답에 유저의 태그 리스트를 포함하여 반환
        return EditUserTagsDto.EditUserTagsResponse.builder()
                .tagNameList(savedTagNameList)
                .build();
    }
}