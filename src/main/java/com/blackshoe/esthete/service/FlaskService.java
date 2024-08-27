package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.FlaskDto;

import java.util.List;

public interface FlaskService {
    List<FlaskDto.UserInfo> sendUserInfosToFlask();
    List<FlaskDto.ExhibitionInfo> sendExhibitionInfosToFlask();
    void saveRecommendations(List<FlaskDto.RecommendationInfo> recommendationInfos);
}