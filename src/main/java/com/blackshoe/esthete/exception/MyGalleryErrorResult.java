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