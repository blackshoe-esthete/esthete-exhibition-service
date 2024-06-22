package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.blackshoe.esthete.dto.EditUserProfileDto;
import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.MyGalleryDto;
import com.blackshoe.esthete.service.MyGalleryService;
import com.blackshoe.esthete.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mygallery")
@RequiredArgsConstructor
public class MyGalleryController {
     private final MyGalleryService myGalleryService;
     private final UserService userService;

    // 사용자의 선호 태그를 수정하는 API
    @PutMapping("/edit/user/tags")
    public ResponseEntity<ApiResponse<EditUserTagsDto.TagList>> editUserTags(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody EditUserTagsDto.TagList tagList) {

        EditUserTagsDto.TagList editUserTagsResponse = myGalleryService.editUserTags(authorizationHeader, tagList);
        return ApiResponse.onSuccess(SuccessStatus.EDIT_USER_TAGS, editUserTagsResponse);
    }

    // 사용자의 프로필 이미지를 수정하는 API
    @PutMapping("/edit/user/profile/img")
    public ResponseEntity<ApiResponse<EditUserProfileDto.EditUserProfileImgResponse>> editUserProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestPart("file") MultipartFile multipartFile) {

        EditUserProfileDto.EditUserProfileImgResponse editUserProfileResponse = userService.editUserProfileImg(authorizationHeader, multipartFile);
        return ApiResponse.onSuccess(SuccessStatus.EDIT_USER_PROFILE_IMG, editUserProfileResponse);
    }

    // 사용자의 프로필 정보를 수정하는 API
    @PutMapping("/edit/user/profile/infos")
    public ResponseEntity<ApiResponse<EditUserProfileDto.EditUserProfileInfosResponse>> editUserProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody EditUserProfileDto.EditUserProfileInfosRequest editUserProfileInfosRequest) {

        EditUserProfileDto.EditUserProfileInfosResponse editUserProfileInfosResponse = userService.editUserProfileInfos(authorizationHeader, editUserProfileInfosRequest);
        return ApiResponse.onSuccess(SuccessStatus.EDIT_USER_PROFILE_INFOS, editUserProfileInfosResponse);
    }

    // 임시저장 전시회를 조회하는 API
    @GetMapping("/temp-exhibitions")
    public ResponseEntity<ApiResponse<List<MyGalleryDto.TemporaryExhibitionResponse>>> getTemporaryExhibitions(
            @RequestHeader("Authorization") String authorizationHeader) {

        List<MyGalleryDto.TemporaryExhibitionResponse> temporaryExhibitionResponses = myGalleryService.getTemporaryExhibitions(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus.GET_ALL_TEMPORARY_EXHIBITIONS, temporaryExhibitionResponses);
    }

    // 임시저장 전시회를 상세 조회하는 API
    @GetMapping("/temp-exhibitions/{temp_exhibitions_id}")
    public ResponseEntity<ApiResponse<MyGalleryDto.TemporaryExhibitionDetailResponse>> getTemporaryExhibitionDetails(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("temp_exhibitions_id") String tempExhibitionId) {

        MyGalleryDto.TemporaryExhibitionDetailResponse temporaryExhibitionDetailResponse = myGalleryService.getTemporaryExhibitionDetails(authorizationHeader, tempExhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.GET_ALL_TEMPORARY_EXHIBITION_DETAIL, temporaryExhibitionDetailResponse);
    }

    // 임시저장 전시회를 삭제하는 API
    @DeleteMapping("/temp-exhibitions/{temp_exhibitions_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> removeTemporaryExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("temp_exhibitions_id") String tempExhibitionId) {

        myGalleryService.removeTemporaryExhibition(authorizationHeader, tempExhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.REMOVE_TEMPORARY_EXHIBITION);
    }

    // 작가 소개를 조회하는 API
    @GetMapping(value = {"/authors/{user_id}", "/authors"})
    public ResponseEntity<ApiResponse<MyGalleryDto.AuthorIntroductionResponse>> getAuthorDetails(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @PathVariable(name = "user_id", required = false) String userId) {

        MyGalleryDto.AuthorIntroductionResponse authorIntroductionResponse = myGalleryService.getAuthorDetails(authorizationHeader, userId);
        return ApiResponse.onSuccess(SuccessStatus.GET_AUTHOR_INTRODUCTIONS, authorIntroductionResponse);
    }

    // 전시를 전체 조회하는 API
    @GetMapping(value = {"/exhibitions/{user_id}", "/exhibitions"})
    public ResponseEntity<ApiResponse<List<MyGalleryDto.ExhibitionResponse>>> getAllExhibitions(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @PathVariable(name = "user_id", required = false) String userId) {

        List<MyGalleryDto.ExhibitionResponse> exhibitionResponses = myGalleryService.getAllExhibitions(authorizationHeader, userId);
        return ApiResponse.onSuccess(SuccessStatus.GET_ALL_EXHIBITIONS, exhibitionResponses);
    }

    // 좋아요 전시를 조회하는 API
    @GetMapping("/exhibitions/likes")
    public ResponseEntity<ApiResponse<List<MyGalleryDto.LikeExhibitionResponse>>> getLikeExhibitions(
            @RequestHeader("Authorization") String authorizationHeader) {

        List<MyGalleryDto.LikeExhibitionResponse> exhibitionResponses = myGalleryService.getLikeExhibitions(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus.GET_LIKE_EXHIBITIONS, exhibitionResponses);
    }

    // 전시 좋아요를 등록하는 API
    @PostMapping("/exhibitions/likes/{exhibition_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> addLikeToExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("exhibition_id") String exhibitionId) {

        myGalleryService.addLikeToExhibition(authorizationHeader, exhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.ADD_LIKE_TO_EXHIBITION);
    }

    // 전시 좋아요를 취소하는 API
    @DeleteMapping("/exhibitions/likes/{exhibition_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> removeLikeToExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("exhibition_id") String exhibitionId) {

        myGalleryService.removeLikeToExhibition(authorizationHeader, exhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.REMOVE_LIKE_TO_EXHIBITION);
    }

    // 내 전시를 삭제하는 API
    @DeleteMapping("/exhibitions/{exhibition_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> removeExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("exhibition_id") String exhibitionId) {

        myGalleryService.removeExhibition(authorizationHeader, exhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.REMOVE_EXHIBITION);
    }

    // 팔로워를 조회하는 API
    @GetMapping("/followers")
    public ResponseEntity<ApiResponse<List<MyGalleryDto.FollowerResponse>>> getFollowers(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(name = "user_id", required = false) String userId,
            @RequestParam(name = "keyword", required = false) String keyword) {

        List<MyGalleryDto.FollowerResponse> followerResponses =  myGalleryService.getFollowers(authorizationHeader, userId, keyword);
        return ApiResponse.onSuccess(SuccessStatus.GET_FOLLOWERS, followerResponses);
    }
}