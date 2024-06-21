package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.TemporaryExhibition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MyGalleryDto {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemporaryExhibitionResponse {
        private UUID temporaryExhibitionId;
        private String thumbnailUrl;
        private String date;

        public static TemporaryExhibitionResponse of(TemporaryExhibition temporaryExhibition) {
            return TemporaryExhibitionResponse.builder()
                    .temporaryExhibitionId(temporaryExhibition.getTemporaryExhibitionId())
                    .thumbnailUrl(temporaryExhibition.getThumbnailUrl())
                    .date(temporaryExhibition.getCreatedAt().format(DATE_FORMATTER))
                    .build();
        }

        public static List<TemporaryExhibitionResponse> of(List<TemporaryExhibition> temporaryExhibitions) {
            return temporaryExhibitions.stream()
                    .map(TemporaryExhibitionResponse::of)
                    .collect(Collectors.toList());
        }
    }
}