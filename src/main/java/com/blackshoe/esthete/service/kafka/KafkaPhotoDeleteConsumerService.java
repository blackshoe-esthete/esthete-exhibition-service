package com.blackshoe.esthete.service.kafka;

import org.springframework.kafka.support.Acknowledgment;

public interface KafkaPhotoDeleteConsumerService {
    void deletePhoto(String payload, Acknowledgment acknowledgment);
}
