package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.service.MyGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mygallery")
@RequiredArgsConstructor
public class MyGalleryController {
    private final MyGalleryService myGalleryService;

    // 사용자의 선호 태그를 수정하는 API
    @PutMapping("/edit/user/tags")
    public ResponseEntity<ApiResponse<Object>> editUserTags(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody EditUserTagsDto.EditUserTagsRequest editUserTagsRequest) {

        EditUserTagsDto.EditUserTagsResponse editUserTagsResponse = myGalleryService.editUserTags(authorizationHeader, editUserTagsRequest);
        return ApiResponse.onSuccess(SuccessStatus._EDIT_USER_TAGS, editUserTagsResponse);
    }
}