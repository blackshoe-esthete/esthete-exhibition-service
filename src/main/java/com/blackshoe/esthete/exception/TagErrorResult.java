package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TagErrorResult implements BaseErrorCode {
    NOT_FOUND_TAG(HttpStatus.NOT_FOUND, "404", "존재하지 않는 태그입니다."),
    DUPLICATE_TAG(HttpStatus.CONFLICT, "409", "중복된 태그가 존재합니다."),
    NOT_FOUND_EXHIBITION_TAGS(HttpStatus.NOT_FOUND, "404", "전시 태그 목록을 찾는데 실패했습니다.")
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