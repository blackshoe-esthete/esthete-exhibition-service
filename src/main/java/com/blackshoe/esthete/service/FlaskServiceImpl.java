package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.FlaskDto;
import com.blackshoe.esthete.entity.UserTag;
import com.blackshoe.esthete.exception.FlaskErrorResult;
import com.blackshoe.esthete.exception.FlaskException;
import com.blackshoe.esthete.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlaskServiceImpl implements FlaskService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final RecommendationRepository recommendationRepository;

    // Flask로 유저 정보를 반환하는 메서드
    @Override
    @Transactional
    public List<FlaskDto.UserInfo> sendUserInfosToFlask() {
        return userRepository.findAll().stream()
                .map(user -> {
                    List<UserTag> userTags = userTagRepository.findAllByUserId(user.getId())
                            .orElse(Collections.emptyList());

                    if (userTags.isEmpty()) {
                        return null;
                    }

                    List<String> tagNames = userTags.stream()
                            .map(userTag -> userTag.getTag().getName())
                            .distinct()
                            .collect(Collectors.toList());

                    return FlaskDto.UserInfo.builder()
                            .userId(user.getId())
                            .userTagNames(tagNames)
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    // Flask로 전시 정보를 반환하는 메서드
    @Override
    @Transactional
    public List<FlaskDto.ExhibitionInfo> sendExhibitionInfosToFlask() {
        return exhibitionRepository.findAll().stream()
                .map(exhibition -> {
                    List<String> tagNames = exhibitionTagRepository.findAllByExhibitionId(exhibition.getId())
                            .orElseThrow(() -> new FlaskException(FlaskErrorResult.NOT_FOUND_ALL_EXHIBITION_TAGS))
                            .stream()
                            .map(exhibitionTag -> exhibitionTag.getTag().getName())
                            .distinct()
                            .collect(Collectors.toList());

                    return FlaskDto.ExhibitionInfo.builder()
                            .exhibitionId(exhibition.getId())
                            .exhibitionTagNames(tagNames)
                            .build();
                })
                .collect(Collectors.toList());
    }

    // Flask에서 받은 추천 전시회를 Redis에 저장하는 메서드
    @Override
    @Transactional
    public void saveRecommendations(List<FlaskDto.RecommendationInfo> recommendationInfos) {
        // 기존 데이터 백업
        recommendationRepository.backupData();

        try {
            // 기존 데이터 삭제
            recommendationRepository.clearData();

            // 새로운 데이터 저장
            recommendationInfos.forEach(recommendationInfo -> {
                Long userId = recommendationInfo.getUserId();
                recommendationInfo.getRecommendations().stream()
                        .map(FlaskDto.Recommendation::getExhibitionId)
                        .filter(exhibitionId -> exhibitionId != 0)
                        .forEach(exhibitionId -> recommendationRepository.save(userId, exhibitionId));
            });

            // 새로운 데이터가 성공적으로 저장되었으면 백업 데이터 삭제
            recommendationRepository.deleteBackupData();
        } catch (Exception e) {
            // 데이터 저장에 실패했으면 백업 데이터로 복원
            recommendationRepository.restoreData();
            throw e;
        }
    }
}