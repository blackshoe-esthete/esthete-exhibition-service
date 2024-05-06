package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.UploadExhibitionDto;

public interface MyGalleryService {
    EditUserTagsDto.EditUserTagsResponse editUserTags(String authorizationHeader, EditUserTagsDto.EditUserTagsRequest editUserTagsRequest);
    UploadExhibitionDto.UploadExhibitionResponse uploadExhibition(String authorizationHeader, UploadExhibitionDto.UploadExhibitionRequest uploadExhibitionRequest);
}