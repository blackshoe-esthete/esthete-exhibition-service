package com.blackshoe.esthete.service;


import com.blackshoe.esthete.dto.EditUserTagsDto;

import java.util.UUID;

public interface MyGalleryService {
    EditUserTagsDto.EditUserTagsResponse editUserTags(UUID userId, EditUserTagsDto.EditUserTagsRequest editUserTagsRequest);
}