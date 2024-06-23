package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExhibitionErrorResult implements BaseErrorCode {
    NOT_FOUND_TEMPORARY_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 임시저장 전시입니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO(HttpStatus.NOT_FOUND, "404", "임시저장 전시에서 사진을 찾을 수 없습니다."),
    NOT_FOUND_EXHIBITION_PHOTO(HttpStatus.NOT_FOUND, "404", "전시에서 사진을 찾을 수 없습니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION_TAG(HttpStatus.NOT_FOUND, "404", "임시저장 전시에서 태그를 찾을 수 없습니다."),
    NOT_FOUND_EXHIBITION_TAG(HttpStatus.NOT_FOUND, "404", "전시에서 태그를 찾을 수 없습니다."),
    NOT_FOUND_VIEW(HttpStatus.NOT_FOUND, "404", "VIEW가 존재하지 않습니다."),
    NOT_FOUND_TAG(HttpStatus.NOT_FOUND, "404", "존재하지 않는 태그입니다."),
    NOT_FOUND_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 전시입니다."),
    NOT_FOUND_LIKE_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 좋아요 전시입니다."),
    NOT_FOUND_EXHIBITIONS(HttpStatus.NOT_FOUND, "404", "전체 전시회를 가져오는 데 실패했습니다."),
    NOT_FOUND_EXHIBITION_LOCATION(HttpStatus.NOT_FOUND, "404", "전시 위치가 존재하지 않습니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION_LOCATION(HttpStatus.NOT_FOUND, "404", "임시저장 전시 위치가 존재하지 않습니다."),
    INVALID_EXHIBITION_PHOTO_IMG_SIZE(HttpStatus.UNPROCESSABLE_ENTITY, "422", "유효하지 않은 전시 사진입니다."),
    EXHIBITION_PHOTO_IMG_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "400", "전시 사진 S3 업로드 실패했습니다."),
    NOT_FOUND_PHOTO_URL(HttpStatus.NOT_FOUND, "404", "존재하지 않는 사진 url입니다."),
    INVALID_LOCATION_GROUP_TYPE(HttpStatus.UNPROCESSABLE_ENTITY, "422", "유효하지 않은 그룹 주소 타입입니다."),
    INVALID_ADDRESS_FILTER(HttpStatus.UNPROCESSABLE_ENTITY, "422", "유효하지 않은 전시 주소 필터입니다."),
    INVALID_SORT_TYPE(HttpStatus.UNPROCESSABLE_ENTITY, "422", "유효하지 않은 정렬 타입입니다."),
    FAIL_TO_GET_SIX_EXHIBITIONS(HttpStatus.NOT_FOUND, "404", "전시회 6개를 가져오는 데 실패했습니다."),
    CONTENT_OVER_LIMIT_LENGTH(HttpStatus.BAD_REQUEST, "400", "컨텐츠가 50자를 넘습니다."),
    CANNOT_COMMENT_ON_OWN_POST(HttpStatus.BAD_REQUEST, "400", "본인 게시물에는 댓글을 달 수 없습니다."),
    IS_NOT_USERS_COMMENT(HttpStatus.BAD_REQUEST, "400", "해당 유저 전시회에 달린 댓글이 아닙니다."),
    IS_ALREADY_LIKED(HttpStatus.CONFLICT, "409", "이미 좋아요 된 댓글입니다."),
    IS_NOT_LIKED(HttpStatus.BAD_REQUEST, "400", "좋아요 된 댓글이 아닙니다."),
    NOT_FOUND_LIKE(HttpStatus.NOT_FOUND, "404", "좋아요 내역을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}