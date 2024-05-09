package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExhibitionException extends RuntimeException {
    private final ExhibitionErrorResult exhibitionErrorResult;

    @Override
    public String getMessage() {
        return exhibitionErrorResult.getMessage();
    }
}