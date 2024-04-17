package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.service.JwtService;
import com.blackshoe.esthete.service.MyGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/mygallery")
@RequiredArgsConstructor
public class MyGalleryController {
    private final MyGalleryService myGalleryService;
    private final JwtService jwtService;

    // 사용자의 선호 태그를 수정하는 API
    @PutMapping("/edit/user/tags")
    public ApiResponse<EditUserTagsDto.EditUserTagsResponse> editUserTags(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody EditUserTagsDto.EditUserTagsRequest editUserTagsRequest) {

        UUID userId = jwtService.extractUserId(accessToken);

        EditUserTagsDto.EditUserTagsResponse editUserTagsResponse = myGalleryService.editUserTags(userId, editUserTagsRequest);

        return ApiResponse.onSuccess(SuccessStatus._EDIT_USER_TAGS.getMessage(), editUserTagsResponse);
    }
}