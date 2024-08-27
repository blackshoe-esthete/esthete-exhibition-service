package com.blackshoe.esthete.service.kafka;


import com.blackshoe.esthete.dto.KafkaConsumerDto;
import com.blackshoe.esthete.entity.Photo;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.exception.KafkaErrorResult;
import com.blackshoe.esthete.exception.KafkaException;
import com.blackshoe.esthete.repository.PhotoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaPhotoDeleteConsumerServiceImpl implements KafkaPhotoDeleteConsumerService{
    private final ObjectMapper objectMapper;
    private final PhotoRepository photoRepository;
    @Override
    public void deletePhoto(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.DeletePhoto deletePhoto = null;

        try {
            // 역직렬화
            deletePhoto = objectMapper.readValue(payload, KafkaConsumerDto.DeletePhoto.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        if (deletePhoto != null) {
            UUID photoId = UUID.fromString(deletePhoto.getPhotoId());
            Photo findDeletePhoto = photoRepository.findByPhotoId(photoId).orElseThrow(
                    () -> new KafkaException(KafkaErrorResult.PHOTO_NOT_FOUND)
            );

            photoRepository.delete(findDeletePhoto);
        }

        log.info("photo info : {}", deletePhoto);
        acknowledgment.acknowledge();
    }
}
