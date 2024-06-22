package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.KafkaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaFilterInfoConsumerServiceImpl implements KafkaFilterInfoConsumerService{
    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = "filter-create")
    @Transactional
    public void createFilter(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaDto.FilterInfo filterInfo = null;

        try {
            // 역직렬화
            filterInfo = objectMapper.readValue(payload, KafkaDto.FilterInfo.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        log.info("User info : {}", filterInfo);
        acknowledgment.acknowledge();
    }
}
