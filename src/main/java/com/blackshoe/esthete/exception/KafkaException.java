package com.blackshoe.esthete.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KafkaException extends RuntimeException {
    private final KafkaErrorResult kafkaErrorResult;

    @Override
    public String getMessage() {
        return kafkaErrorResult.getMessage();
    }
}
