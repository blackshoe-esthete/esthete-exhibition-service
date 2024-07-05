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
public class KafkaUserDeleteProducerServiceImpl implements KafkaUserDeleteProducerService {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @Override
    public void deleteUser(KafkaProducerDto.UserDelete userDelete) {
        String topic = "user-delete";

        String deleteUserJsonString;
        try {
            deleteUserJsonString = objectMapper.writeValueAsString(userDelete);
        } catch (JsonProcessingException e) {
            log.error("Error while converting photo object to json string", e);
            //throw new KafkaException(() -> KafkaErrorResult.JSON_CONVERSION_ERROR);
            throw new KafkaException(String.valueOf(KafkaErrorResult.JSON_CONVERSION_ERROR));
        }

        kafkaProducer.send(topic, deleteUserJsonString);
    }
}
