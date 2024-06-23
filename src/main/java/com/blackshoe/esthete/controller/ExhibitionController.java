package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionLocationGroupType;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.common.vo.ExhibitionSortType;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.dto.MainHomeDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/map/location/current")
    public ResponseEntity<ApiResponse<Page<ExhibitionClusteringDto.MarkedRegionGroupResponse>>> getTop10ByUserLocationGroupBy(
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "radius") double radius,
            @RequestParam(name = "group") String group) {
        ExhibitionPointFilter exhibitionLocationFilter = ExhibitionPointFilter.builder()
                .longitude(longitude)
                .latitude(latitude)
                .radius(radius)
                .build();


        ExhibitionLocationGroupType exhibitionLocationGroupType = ExhibitionLocationGroupType.convertParamToColumn(group);
        Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> markedRegionGroupResponse = exhibitionService.getTop10ByUserLocationGroupBy(exhibitionLocationFilter, exhibitionLocationGroupType);

        return ApiResponse.onSuccess(SuccessStatus.GET_EXHIBITION_GROUP_IN_MAP, markedRegionGroupResponse);

    }

    @GetMapping("/map/location")
    public ResponseEntity<ApiResponse<Page<ExhibitionClusteringDto.MarkedExhibitionsResponse>>> readByAddress (
            @RequestParam(name = "state", required = true) Optional<String> state,
            @RequestParam(name = "city", required = false) Optional<String> city,
            @RequestParam(name = "town", required = false) Optional<String> town,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "sort") String sort) {

        ExhibitionAddressFilter exhibitionAddressFilter = ExhibitionAddressFilter.builder()
                .state(state.orElse(""))
                .city(city.orElse(""))
                .town(town.orElse(""))
                .build();

        Sort sortBy = ExhibitionSortType.convertParamToColumn(sort);

        Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> markedExhibitionsResponse
                = exhibitionService.readByAddress(exhibitionAddressFilter, page, size, sortBy);

        return ApiResponse.onSuccess(SuccessStatus.GET_EXHIBITIONS_IN_MAP, markedExhibitionsResponse);
    }

    // 개인 추천 전시회 조회 API
    @GetMapping("/recommend")
    public ResponseEntity<ApiResponse<List<MainHomeDto.ExhibitionResponse>>> getRecommendExhibitions(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        List<MainHomeDto.ExhibitionResponse> exhibitionResponses = exhibitionService.getRecommendExhibitions(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus.GET_RECOMMEND_EXHIBITIONS, exhibitionResponses);
    }

    // 소외 전시회 조회 API
    @GetMapping("/isolation")
    public ResponseEntity<ApiResponse<List<MainHomeDto.ExhibitionResponse>>> getIsolationExhibitions() {

        List<MainHomeDto.ExhibitionResponse> exhibitionResponses = exhibitionService.getIsolationExhibitions();
        return ApiResponse.onSuccess(SuccessStatus.GET_ISOLATION_EXHIBITIONS, exhibitionResponses);
    }

    // 태그 선택 전시회 조회 API
    @GetMapping("/tags/{tag_name}")
    public ResponseEntity<ApiResponse<List<MainHomeDto.ExhibitionResponse>>> getExhibitionsByTag(
            @PathVariable("tag_name") String tagName) {

        List<MainHomeDto.ExhibitionResponse> exhibitionResponses = exhibitionService.getExhibitionsByTag(tagName);
        return ApiResponse.onSuccess(SuccessStatus.GET_TAG_EXHIBITIONS, exhibitionResponses);
    }

    // 주변 전시회 조회 API
    @GetMapping("/location")
    public ResponseEntity<ApiResponse<List<MainHomeDto.ExhibitionResponse>>> getNearByExhibitions(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude) {

        List<MainHomeDto.ExhibitionResponse> exhibitionResponses = exhibitionService.getNearByExhibitions(authorizationHeader, longitude, latitude);
        return ApiResponse.onSuccess(SuccessStatus.GET_NEARBY_EXHIBITIONS, exhibitionResponses);
    }

    // 개인 선호 작가 조회 API
    @GetMapping("/authors")
    public ResponseEntity<ApiResponse<List<MainHomeDto.AuthorResponse>>> getPreferAuthors(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        List<MainHomeDto.AuthorResponse> exhibitionResponses = exhibitionService.getPreferAuthors(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus.GET_PREFER_AUTHORS, exhibitionResponses);
    }

    // 전시회 상세 조회 API
    @GetMapping("/details/{exhibition_id}")
    public ResponseEntity<ApiResponse<MainHomeDto.ExhibitionDetailResponse>> getExhibitionDetails(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable("exhibition_id") String exhibitionId) {

        MainHomeDto.ExhibitionDetailResponse exhibitionDetailResponse = exhibitionService.getExhibitionDetails(authorizationHeader, exhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.GET_TAG_EXHIBITION_DETAILS, exhibitionDetailResponse);
    }

    // 전시회 댓글 전체 조회 API
    @GetMapping("/comments/{exhibition_id}")
    public ResponseEntity<ApiResponse<List<MainHomeDto.CommentResponse>>> getAllComments(
            @PathVariable("exhibition_id") String exhibitionId) {

        List<MainHomeDto.CommentResponse> commentResponses = exhibitionService.getAllComments(exhibitionId);
        return ApiResponse.onSuccess(SuccessStatus.GET_ALL_COMMENTS, commentResponses);
    }

    // 전시회 댓글 등록 API
    @PostMapping("/comments")
    public ResponseEntity<ApiResponse<SuccessStatus>> addComments(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody MainHomeDto.CommentRequest commentRequest) {

        exhibitionService.addComments(authorizationHeader, commentRequest);
        return ApiResponse.onSuccess(SuccessStatus.ADD_COMMENTS);
    }

    // 전시회 댓글 좋아요 등록 API
    @PostMapping("/comments/like/{comment_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> addLikeToComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("comment_id") String commentId) {

        exhibitionService.addLikeToComment(authorizationHeader, commentId);
        return ApiResponse.onSuccess(SuccessStatus.ADD_LIKE_TO_COMMENT);
    }

    // 전시회 댓글 좋아요 취소 API
    @DeleteMapping("/comments/like/{comment_id}")
    public ResponseEntity<ApiResponse<SuccessStatus>> removeLikeToComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("comment_id") String commentId) {

        exhibitionService.removeLikeToComment(authorizationHeader, commentId);
        return ApiResponse.onSuccess(SuccessStatus.REMOVE_LIKE_TO_COMMENT);
    }

    // 전시회 댓글 신고 API
    @DeleteMapping("/comments/report")
    public ResponseEntity<ApiResponse<SuccessStatus>> reportComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody MainHomeDto.ReportCommentRequest reportCommentRequest) {

        exhibitionService.reportComment(authorizationHeader, reportCommentRequest);
        return ApiResponse.onSuccess(SuccessStatus.REPORT_TO_COMMENT);
    }
}