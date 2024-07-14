package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum KafkaErrorResult {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."), //404
    JSON_CONVERSION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 변환 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
