package com.blackshoe.esthete.controller;


import com.blackshoe.esthete.dto.FlaskDto;
import com.blackshoe.esthete.service.FlaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flask")
@RequiredArgsConstructor
public class FlaskController {
    private final FlaskService flaskService;

    // 유저 정보 전달 API
    @GetMapping("/users")
    public List<FlaskDto.UserInfo> sendUserInfosToFlask() {
        return flaskService.sendUserInfosToFlask();
    }

    // 전시 정보 전달 API
    @GetMapping("/exhibitions")
    public List<FlaskDto.ExhibitionInfo> sendExhibitionInfosToFlask() {
        return flaskService.sendExhibitionInfosToFlask();
    }

    // 추천 전시회 목록 업데이트 API
    @PostMapping("/recommendations")
    public void receiveRecommendationsFromFlask(@RequestBody List<FlaskDto.RecommendationInfo> recommendationInfos) {
        for (FlaskDto.RecommendationInfo recommendationInfo : recommendationInfos) {
            Long userId = recommendationInfo.getUserId();
            List<FlaskDto.Recommendation> recommendations = recommendationInfo.getRecommendations();

            // 추후 Redis에 저장하는 방식으로 변경할 예정
            System.out.println("User ID: " + userId);
            for (FlaskDto.Recommendation recommendation : recommendations) {
                Long exhibitionId = recommendation.getExhibitionId();
                Float weight = recommendation.getWeight();
                System.out.println("- Exhibition ID: " + exhibitionId + " | Weight: " + weight);
            }
        }
    }
}
