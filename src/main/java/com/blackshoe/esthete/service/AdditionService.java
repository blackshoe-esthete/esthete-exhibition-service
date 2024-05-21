package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.CreateExhibitionDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AdditionService {

    CreateExhibitionDto.CreateTmpExhibitionResponse saveTemporaryExhibition(UUID userId, List<MultipartFile> exhibitionPhotos, CreateExhibitionDto.CreateExhibitionRequest requestDto);

    CreateExhibitionDto.CreateExhibitionResponse saveExhibition(UUID userId, List<MultipartFile> exhibitionPhotos, CreateExhibitionDto.CreateExhibitionRequest requestDto);
}
