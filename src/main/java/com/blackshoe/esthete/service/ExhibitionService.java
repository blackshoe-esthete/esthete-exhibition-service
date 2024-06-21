package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.MainHomeDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExhibitionService {
    Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size);

    Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibition(String exhibitionKeyword, int page, int size);

    Page<SearchExhibitionDto.SearchAuthorResponse> searchAllAuthor(int page, int size);

    Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthor(String authorKeyword, int page, int size);

    List<MainHomeDto.ExhibitionResponse> getRecommendExhibitions(String authorizationHeader);
}