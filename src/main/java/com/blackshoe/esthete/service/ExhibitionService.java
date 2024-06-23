package com.blackshoe.esthete.service;

import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionLocationGroupType;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.dto.MainHomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ExhibitionService {
    Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size);
    Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibition(String exhibitionKeyword, int page, int size);
    Page<SearchExhibitionDto.SearchAuthorResponse> searchAllAuthor(int page, int size);
    Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthor(String authorKeyword, int page, int size);
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> getTop10ByUserLocationGroupBy(ExhibitionPointFilter exhibitionLocationFilter, ExhibitionLocationGroupType exhibitionLocationGroupType);
    Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> readByAddress(ExhibitionAddressFilter exhibitionAddressFilter, Integer page, Integer size, Sort sortBy);
    List<MainHomeDto.ExhibitionResponse> getRecommendExhibitions(String authorizationHeader);
    List<MainHomeDto.ExhibitionResponse> getIsolationExhibitions();
    List<MainHomeDto.ExhibitionResponse> getExhibitionsByTag(String tagName);
    List<MainHomeDto.ExhibitionResponse> getNearByExhibitions(String authorizationHeader, Double longitude, Double latitude);
    List<MainHomeDto.AuthorResponse> getPreferAuthors(String authorizationHeader);
    MainHomeDto.ExhibitionDetailResponse getExhibitionDetails(String authorizationHeader, String exhibitionId);
    List<MainHomeDto.CommentResponse> getAllComments(String exhibitionId);
    void addComments(String authorizationHeader, MainHomeDto.CommentRequest commentRequest);
    void addLikeToComment(String authorizationHeader, String commentId);
    void removeLikeToComment(String authorizationHeader, String commentId);
    void reportComment(String authorizationHeader, MainHomeDto.ReportCommentRequest reportCommentRequest);
}