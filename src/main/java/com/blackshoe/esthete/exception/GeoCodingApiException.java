package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeoCodingApiException extends RuntimeException{
    private final GeoCodingApiErrorResult geoCodingApiErrorResult;

}
