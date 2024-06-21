package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeoCodingApiErrorResult {
    GEO_CODING_SERVICE_4XX_ERROR(HttpStatus.NOT_FOUND, "지오코딩 서비스 4xx 에러"),
    GEO_CODING_SERVICE_5XX_ERROR(HttpStatus.NOT_FOUND, "지오코딩 서비스 5xx 에러"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
