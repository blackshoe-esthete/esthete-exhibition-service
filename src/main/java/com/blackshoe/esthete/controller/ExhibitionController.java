package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.blackshoe.esthete.dto.MainHomeDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    // 토큰이 없는 경우에도 검색이 가능함 -> 토큰 없이 검색

    @GetMapping("/searching/title")
    public ResponseEntity<ApiResponse<Page<SearchExhibitionDto.SearchExhibitionResponse>>> searchFilterWithExhibition(
            @RequestParam(required = false) String exhibitionKeyword,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        if(exhibitionKeyword == null){ // 아무것도 검색하지 않은 상태 -> viewCount가 높은 순으로 모두 보여줌
            Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibitionResponses = exhibitionService.searchAllExhibition(page, size);
            return ApiResponse.onSuccess(SuccessStatus.SEARCH_ALL_EXHIBITION, searchExhibitionResponses);
        }else{ // 전시명을 입력한 상태 -> 검색명을 포함한 전시 중 viewCount가 높은 순으로 보여줌
            Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibitionResponses = exhibitionService.searchExhibition(exhibitionKeyword, page, size);
            return ApiResponse.onSuccess(SuccessStatus.SEARCH_EXHIBITION_BY_KEYWORD, searchExhibitionResponses);
        }
    }

    @GetMapping("/searching/author")
    public ResponseEntity<ApiResponse<Page<SearchExhibitionDto.SearchAuthorResponse>>> searchFilterWithAuthor(
            @RequestParam(required = false) String authorKeyword,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        if(authorKeyword == null){ // 아무것도 검색하지 않은 상태 -> 팔로우가 많은 순으로 모두 보여줌
            Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthorResponses = exhibitionService.searchAllAuthor(page, size);
            return ApiResponse.onSuccess(SuccessStatus.SEARCH_ALL_AUTHOR, searchAuthorResponses);
        }else{ // 작가명을 입력한 상태 -> 검색명을 포함한 작가 이름 중 팔로우가 많은 순으로 보여줌
            Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthorResponses = exhibitionService.searchAuthor(authorKeyword, page, size);
            return ApiResponse.onSuccess(SuccessStatus.SEARCH_AUTHOR_BY_KEYWORD, searchAuthorResponses);
        }
    }

    @GetMapping("/recommend")
    public ResponseEntity<ApiResponse<List<MainHomeDto.ExhibitionResponse>>> getRecommendExhibitions(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        List<MainHomeDto.ExhibitionResponse> exhibitionResponses = exhibitionService.getRecommendExhibitions(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus.GET_RECOMMEND_EXHIBITIONS, exhibitionResponses);
    }
}
