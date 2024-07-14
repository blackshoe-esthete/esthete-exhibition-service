package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.KafkaProducerDto;

public interface KafkaUserEditProducerService {
    void editProfileImgUrl(KafkaProducerDto.UserProfileImgUrl userProfileImgUrl);
}