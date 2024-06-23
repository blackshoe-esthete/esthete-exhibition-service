package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.MyGalleryDto;

import java.util.List;

public interface MyGalleryService {
    EditUserTagsDto.TagList editUserTags(String authorizationHeader, EditUserTagsDto.TagList tagList);
    List<MyGalleryDto.TemporaryExhibitionResponse> getTemporaryExhibitions(String authorizationHeader);
    MyGalleryDto.TemporaryExhibitionDetailResponse getTemporaryExhibitionDetails(String authorizationHeader, String tempExhibitionId);
    void removeTemporaryExhibition(String authorizationHeader, String tempExhibitionId);
    MyGalleryDto.AuthorIntroductionResponse getAuthorDetails(String authorizationHeader, String userId);
    List<MyGalleryDto.ExhibitionResponse> getAllExhibitions(String authorizationHeader, String userId);
    List<MyGalleryDto.LikeExhibitionResponse> getLikeExhibitions(String authorizationHeader);
    void addLikeToExhibition(String authorizationHeader, String exhibitionId);
    void removeLikeToExhibition(String authorizationHeader, String exhibitionId);
    void removeExhibition(String authorizationHeader, String exhibitionId);
    List<MyGalleryDto.FollowerResponse> getFollowers(String authorizationHeader, String userId, String keyword);
    List<MyGalleryDto.FollowingResponse> getFollowings(String authorizationHeader, String userId, String keyword);
    void addFollow(String authorizationHeader, String userId);
    void removeFollow(String authorizationHeader, String userId);
    void deleteUser(String authorizationHeader);
}