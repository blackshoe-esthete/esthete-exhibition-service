package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MyGalleryErrorResult implements BaseErrorCode {
    FAIL_TO_GET_TEMPORARY_EXHIBITIONS(HttpStatus.NOT_FOUND, "404", "임시저장 전시회를 불러오는 데 실패했습니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 임시저장 전시회입니다."),
    UNAUTHORIZED_TEMPORARY_EXHIBITION(HttpStatus.UNAUTHORIZED, "401", "사용자의 임시저장 전시회가 아닙니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "올바른 요청이 아닙니다."),
    IS_ALREADY_LIKED(HttpStatus.CONFLICT, "409", "이미 좋아요를 누른 전시회입니다."),
    IS_ALREADY_NOT_LIKED(HttpStatus.CONFLICT, "409", "이미 좋아요가 없는 전시회입니다."),
    CANNOT_LIKE_ON_OWN_EXHIBITION(HttpStatus.BAD_REQUEST, "400", "본인 전시회에는 좋아요를 누를 수 없습니다."),
    FAIL_TO_GET_FOLLOWS(HttpStatus.NOT_FOUND, "404", "팔로우 목록을 불러오는 데 실패했습니다."),
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