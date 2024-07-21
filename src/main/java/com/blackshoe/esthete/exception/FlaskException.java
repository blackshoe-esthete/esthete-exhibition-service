package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FlaskException extends RuntimeException {
    private final FlaskErrorResult flaskErrorResult;

    @Override
    public String getMessage() {
        return flaskErrorResult.getMessage();
    }
}