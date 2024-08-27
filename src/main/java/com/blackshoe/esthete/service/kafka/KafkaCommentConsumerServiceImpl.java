package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.KafkaConsumerDto;
import com.blackshoe.esthete.entity.Comment;
import com.blackshoe.esthete.entity.Photo;
import com.blackshoe.esthete.exception.KafkaErrorResult;
import com.blackshoe.esthete.exception.KafkaException;
import com.blackshoe.esthete.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaCommentConsumerServiceImpl implements KafkaCommentConsumerService{
    private final ObjectMapper objectMapper;
    private final CommentRepository commentRepository;

    @Override
    public void deleteComment(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.DeleteComment deleteComment = null;

        try {
            // 역직렬화
            deleteComment = objectMapper.readValue(payload, KafkaConsumerDto.DeleteComment.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        if (deleteComment != null) {
            UUID commentId = UUID.fromString(deleteComment.getCommentId());
            Comment findComment = commentRepository.findByCommentId(commentId).orElseThrow(
                    () -> new KafkaException(KafkaErrorResult.COMMENT_NOT_FOUND)
            );

            commentRepository.delete(findComment);
        }

        log.info("comment info : {}", deleteComment);
        acknowledgment.acknowledge();
    }
}
