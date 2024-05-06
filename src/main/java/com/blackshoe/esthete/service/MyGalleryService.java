package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.UploadExhibitionDto;

public interface MyGalleryService {
    EditUserTagsDto.TagList editUserTags(String authorizationHeader, EditUserTagsDto.TagList tagList);
    UploadExhibitionDto.UploadExhibitionResponse uploadExhibition(String authorizationHeader, UploadExhibitionDto.UploadExhibitionRequest uploadExhibitionRequest);
}