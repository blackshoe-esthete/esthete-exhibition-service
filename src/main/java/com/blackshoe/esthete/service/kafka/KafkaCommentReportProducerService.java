package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.MainHomeDto;

public interface KafkaCommentReportProducerService {
    void reportComment(MainHomeDto.ReportCommentResponse reportCommentResponse);
}