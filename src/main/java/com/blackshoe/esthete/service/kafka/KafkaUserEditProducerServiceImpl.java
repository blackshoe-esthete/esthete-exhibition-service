package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.KafkaProducerDto;
import com.blackshoe.esthete.exception.KafkaErrorResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaUserEditProducerServiceImpl implements KafkaUserEditProducerService {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @Override
    public void editProfileImgUrl(KafkaProducerDto.UserProfileImgUrl userProfileImgUrl) {
        String topic = "user-set-profile";
        String userProfileImgUrlJsonString;
        try {
            userProfileImgUrlJsonString = objectMapper.writeValueAsString(userProfileImgUrl);
        } catch (JsonProcessingException e) {
            throw new KafkaException(String.valueOf(KafkaErrorResult.JSON_CONVERSION_ERROR));
        }

        kafkaProducer.send(topic, userProfileImgUrlJsonString);
    }
}