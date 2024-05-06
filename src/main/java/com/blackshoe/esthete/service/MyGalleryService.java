package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;

public interface MyGalleryService {
    EditUserTagsDto.EditUserTagsResponse editUserTags(String authorizationHeader, EditUserTagsDto.EditUserTagsRequest editUserTagsRequest);
}