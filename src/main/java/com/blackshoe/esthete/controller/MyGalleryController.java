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
}