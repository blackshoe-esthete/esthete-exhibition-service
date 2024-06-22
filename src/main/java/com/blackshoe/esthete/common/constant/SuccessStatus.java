package com.blackshoe.esthete.common.constant;

import com.blackshoe.esthete.common.code.BaseCode;
import com.blackshoe.esthete.common.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // Global
    _OK(HttpStatus.OK, "200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "201", "성공적으로 생성되었습니다."),
    // MyGallery
    EDIT_USER_TAGS(HttpStatus.OK, "200", "선호 태그 수정에 성공했습니다."),
    EDIT_USER_PROFILE_IMG(HttpStatus.OK, "200", "프로필 사진 수정에 성공했습니다."),
    EDIT_USER_PROFILE_INFOS(HttpStatus.OK, "200", "프로필 정보 수정에 성공했습니다."),
    UPLOAD_EXHIBITION_PHOTO(HttpStatus.CREATED, "201", "전시 사진 추가에 성공했습니다."),
    UPLOAD_EXHIBITION(HttpStatus.CREATED, "201", "전시 제작에 성공했습니다."),
    GET_AUTHOR_INTRODUCTIONS(HttpStatus.OK, "200", "작가 소개 조회에 성공했습니다."),
    GET_ALL_EXHIBITIONS(HttpStatus.OK, "200", "전시 전체 조회에 성공했습니다."),
    GET_LIKE_EXHIBITIONS(HttpStatus.OK, "200", "좋아요 전시 조회에 성공했습니다."),
    ADD_LIKE_TO_EXHIBITION(HttpStatus.CREATED, "201", "전시 좋아요 등록에 성공했습니다."),
    REMOVE_LIKE_TO_EXHIBITION(HttpStatus.OK, "200", "전시 좋아요 취소에 성공했습니다."),
    REMOVE_EXHIBITION(HttpStatus.OK, "200", "내 전시 삭제에 성공했습니다."),
    GET_FOLLOWERS(HttpStatus.OK, "200", "팔로워 조회에 성공했습니다."),
    GET_FOLLOWINGS(HttpStatus.OK, "200", "팔로잉 조회에 성공했습니다."),
    ADD_FOLLOW(HttpStatus.CREATED, "201", "팔로우 등록에 성공했습니다."),
    REMOVE_FOLLOW(HttpStatus.OK, "200", "팔로우 취소에 성공했습니다."),
    GET_ALL_TEMPORARY_EXHIBITIONS(HttpStatus.OK, "200", "임시저장 전시 전체 조회에 성공했습니다."),
    GET_ALL_TEMPORARY_EXHIBITION_DETAIL(HttpStatus.OK, "200", "임시저장 전시 상세 조회에 성공했습니다."),
    REMOVE_TEMPORARY_EXHIBITION(HttpStatus.OK, "200", "임시저장 전시 삭제에 성공했습니다."),
    // Exhibition
    SEARCH_ALL_EXHIBITION(HttpStatus.OK, "200", "전체 전시 검색에 성공했습니다."),
    SEARCH_EXHIBITION_BY_KEYWORD(HttpStatus.OK, "200", "전시 검색에 성공했습니다."),
    SEARCH_ALL_AUTHOR(HttpStatus.OK, "200", "전체 작가 검색에 성공했습니다."),
    SEARCH_AUTHOR_BY_KEYWORD(HttpStatus.OK, "200", "작가 검색에 성공했습니다."),
    GET_EXHIBITION_GROUP_IN_MAP(HttpStatus.OK, "200", "전시 클러스터링에 성공했습니다."),
    GET_EXHIBITIONS_IN_MAP(HttpStatus.OK, "200", "클러스터에 해당하는 전시 리스트 조회를 성공했습니다."),
    GET_RECOMMEND_EXHIBITIONS(HttpStatus.OK, "200", "개인 추천 전시회 조회에 성공했습니다."),
    GET_ISOLATION_EXHIBITIONS(HttpStatus.OK, "200", "소외 전시회 조회에 성공했습니다."),
    GET_TAG_EXHIBITIONS(HttpStatus.OK, "200", "태그 선택 전시회 조회에 성공했습니다."),
    GET_TAG_EXHIBITION_DETAILS(HttpStatus.OK, "200", "전시회 상세 조회에 성공했습니다."),
    ADD_COMMENTS(HttpStatus.CREATED, "201", "댓글 등록에 성공했습니다."),
    GET_ALL_COMMENTS(HttpStatus.OK, "200", "댓글 전체 조회에 성공했습니다."),
    ADD_LIKE_TO_COMMENT(HttpStatus.CREATED, "201", "댓글 좋아요 등록에 성공했습니다."),
    REMOVE_LIKE_TO_COMMENT(HttpStatus.OK, "200", "댓글 좋아요 취소에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}