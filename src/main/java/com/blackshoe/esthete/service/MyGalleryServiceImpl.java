package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.MyGalleryDto;
import com.blackshoe.esthete.entity.Tag;
import com.blackshoe.esthete.entity.TemporaryExhibition;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.entity.UserTag;
import com.blackshoe.esthete.exception.MyGalleryErrorResult;
import com.blackshoe.esthete.exception.MyGalleryException;
import com.blackshoe.esthete.exception.TagErrorResult;
import com.blackshoe.esthete.exception.TagException;
import com.blackshoe.esthete.repository.TagRepository;
import com.blackshoe.esthete.repository.TemporaryExhibitionRepository;
import com.blackshoe.esthete.repository.UserTagRepository;
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
    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;
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

    // 임시 저장 전시회를 전체 조회하는 메서드
    @Override
    public List<MyGalleryDto.TemporaryExhibitionResponse> getTemporaryExhibitions(String authorizationHeader) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        List<TemporaryExhibition> temporaryExhibitions = temporaryExhibitionRepository.findAllByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new MyGalleryException(MyGalleryErrorResult.FAIL_TO_GET_TEMPORARY_EXHIBITIONS));
        return MyGalleryDto.TemporaryExhibitionResponse.of(temporaryExhibitions);
    }
}