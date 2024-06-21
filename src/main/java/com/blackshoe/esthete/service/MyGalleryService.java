package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.EditUserTagsDto;
import com.blackshoe.esthete.dto.MyGalleryDto;
import com.blackshoe.esthete.dto.UploadExhibitionDto;

import java.util.List;

public interface MyGalleryService {
    EditUserTagsDto.TagList editUserTags(String authorizationHeader, EditUserTagsDto.TagList tagList);
    List<MyGalleryDto.TemporaryExhibitionResponse> getTemporaryExhibitions(String authorizationHeader);
}