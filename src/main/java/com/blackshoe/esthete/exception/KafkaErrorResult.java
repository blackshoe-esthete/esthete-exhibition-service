package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum KafkaErrorResult implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "사용자를 찾을 수 없습니다."),
    JSON_CONVERSION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"500", "JSON 변환 중 오류가 발생했습니다."),
    FAIL_TO_SEND_KAFKA(HttpStatus.INTERNAL_SERVER_ERROR,"500", "카프카 전송에 실패했습니다.");

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
