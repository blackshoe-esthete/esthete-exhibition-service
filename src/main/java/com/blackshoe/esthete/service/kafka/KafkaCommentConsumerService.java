package com.blackshoe.esthete.service.kafka;

import org.springframework.kafka.support.Acknowledgment;

public interface KafkaCommentConsumerService {
    void deleteComment(String payload, Acknowledgment acknowledgment);
}
