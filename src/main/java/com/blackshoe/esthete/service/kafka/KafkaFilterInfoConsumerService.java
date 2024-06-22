package com.blackshoe.esthete.service.kafka;

import org.springframework.kafka.support.Acknowledgment;

public interface KafkaFilterInfoConsumerService {
    void createFilter(String payload, Acknowledgment acknowledgment);
}
