package com.blackshoe.esthete.service;

import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionLocationGroupType;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ExhibitionService {
    Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size);

    Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibition(String exhibitionKeyword, int page, int size);

    Page<SearchExhibitionDto.SearchAuthorResponse> searchAllAuthor(int page, int size);

    Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthor(String authorKeyword, int page, int size);

}
