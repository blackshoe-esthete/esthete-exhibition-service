package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LikeException extends RuntimeException {
    private final LikeErrorResult likeErrorResult;

    @Override
    public String getMessage() {
        return likeErrorResult.getMessage();
    }
}