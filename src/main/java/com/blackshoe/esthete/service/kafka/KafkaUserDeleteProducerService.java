package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.KafkaProducerDto;

public interface KafkaUserDeleteProducerService {
    void deleteUser(KafkaProducerDto.UserDelete userDelete);
}
