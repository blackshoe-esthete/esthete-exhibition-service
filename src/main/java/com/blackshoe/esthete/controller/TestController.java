package com.blackshoe.esthete.controller;


import com.blackshoe.esthete.dto.KafkaProducerDto;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.exception.UserErrorResult;
import com.blackshoe.esthete.exception.UserException;
import com.blackshoe.esthete.repository.UserRepository;
import com.blackshoe.esthete.service.kafka.KafkaUserDeleteProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final KafkaUserDeleteProducerService kafkaUserDeleteProducerService;
    private final UserRepository userRepository;

    @PostMapping("/kafka")
    public ResponseEntity<?> TestKafka() {

        KafkaProducerDto.UserDelete userDelete = KafkaProducerDto.UserDelete.builder()
                .userId(UUID.fromString("8649fc4b-e22c-4b6f-8b28-22a93c704561"))
                .build();

        User findUser = userRepository.findByUserId(UUID.fromString("8649fc4b-e22c-4b6f-8b28-22a93c704561"))
                .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
        userRepository.delete(findUser);

        kafkaUserDeleteProducerService.deleteUser(userDelete);

        return ResponseEntity.ok("Kafka User-Delete Test Success!");
    }
}
