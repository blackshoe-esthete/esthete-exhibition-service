package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExhibitionErrorResult implements BaseErrorCode {
    NOT_FOUND_TEMPORARY_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 임시저장 전시입니다.");

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