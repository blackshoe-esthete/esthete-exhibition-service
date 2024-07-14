package com.blackshoe.esthete.service.kafka;

import com.blackshoe.esthete.dto.MainHomeDto;

public interface KafkaPhotoReportProducerService {
    void reportPhoto(MainHomeDto.ReportPhotoResponse reportPhotoResponse);
}