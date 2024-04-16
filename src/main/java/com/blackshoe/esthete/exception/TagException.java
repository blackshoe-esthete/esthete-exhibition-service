package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagException extends RuntimeException{
    private final TagErrorResult tagErrorResult;

    @Override
    public String getMessage() {
        return tagErrorResult.getMessage();
    }
}