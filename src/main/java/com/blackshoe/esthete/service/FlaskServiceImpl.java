package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.FlaskDto;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.ExhibitionTag;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.entity.UserTag;
import com.blackshoe.esthete.exception.FlaskErrorResult;
import com.blackshoe.esthete.exception.FlaskException;
import com.blackshoe.esthete.repository.ExhibitionRepository;
import com.blackshoe.esthete.repository.ExhibitionTagRepository;
import com.blackshoe.esthete.repository.UserRepository;
import com.blackshoe.esthete.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlaskServiceImpl implements FlaskService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;

    // Flask로 유저 정보를 반환하는 메서드
    @Override
    public List<FlaskDto.UserInfo> sendUserInfosToFlask() {
        List<FlaskDto.UserInfo> userInfos = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            List<UserTag> userTags = userTagRepository.findAllByUserId(user.getId())
                    .orElseThrow(() -> new FlaskException(FlaskErrorResult.NOT_FOUND_ALL_USER_TAGS));
            List<String> tagNames = new ArrayList<>();

            // 사용자의 태그 목록에서 태그 이름을 가져와서 리스트에 추가
            for (UserTag userTag : userTags) {
                String tagName = userTag.getTag().getName();

                if (!tagNames.contains(tagName)) {
                    tagNames.add(tagName);
                }
            }

            // UserInfo DTO에 사용자의 ID와 태그 목록 설정
            FlaskDto.UserInfo userInfo = FlaskDto.UserInfo.builder()
                    .userId(user.getId())
                    .userTagNames(tagNames)
                    .build();

            // 리스트에 UserInfo DTO 추가
            userInfos.add(userInfo);
        }

        return userInfos;
    }

    // Flask로 전시 정보를 반환하는 메서드
    @Override
    public List<FlaskDto.ExhibitionInfo> sendExhibitionInfosToFlask() {
        List<FlaskDto.ExhibitionInfo> exhibitionInfos = new ArrayList<>();
        List<Exhibition> exhibitions = exhibitionRepository.findAll();

        for (Exhibition exhibition : exhibitions) {
            List<ExhibitionTag> exhibitionTags = exhibitionTagRepository.findAllByExhibitionId(exhibition.getId())
                    .orElseThrow(() -> new FlaskException(FlaskErrorResult.NOT_FOUND_ALL_EXHIBITION_TAGS));
            List<String> tagNames = new ArrayList<>();

            // 전시회의 태그 목록에서 태그 이름을 가져와서 리스트에 추가
            for (ExhibitionTag exhibitionTag : exhibitionTags) {
                String exhibitionName = exhibitionTag.getTag().getName();

                if (!tagNames.contains(exhibitionName)) {
                    tagNames.add(exhibitionName);
                }
            }

            // ExhibitionInfo DTO에 전시회의 ID와 태그 목록 설정
            FlaskDto.ExhibitionInfo exhibitionInfo = FlaskDto.ExhibitionInfo.builder()
                    .exhibitionId(exhibition.getId())
                    .exhibitionTagNames(tagNames)
                    .build();

            // 리스트에 ExhibitionInfo DTO 추가
            exhibitionInfos.add(exhibitionInfo);
        }

        return exhibitionInfos;
    }
}