package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyGalleryException extends RuntimeException {
    private final MyGalleryErrorResult myGalleryErrorResult;

    @Override
    public String getMessage() {
        return myGalleryErrorResult.getMessage();
    }
}