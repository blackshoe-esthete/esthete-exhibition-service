package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TagErrorResult {
    NOT_FOUND_TAG(HttpStatus.NOT_FOUND, "존재하지 않는 태그입니다."),
    DUPLICATE_TAG(HttpStatus.NOT_FOUND, "중복된 태그가 존재합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}