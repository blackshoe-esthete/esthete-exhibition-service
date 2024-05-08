package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.SearchExhibitionDto;
import org.springframework.data.domain.Page;

public interface ExhibitionService {
    Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size);

    Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibition(String exhibitionKeyword, int page, int size);




}
