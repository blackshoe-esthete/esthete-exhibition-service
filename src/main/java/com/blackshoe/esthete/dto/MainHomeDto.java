package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.Exhibition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MainHomeDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionResponse {
        private UUID exhibitionId;
        private String thumbnailUrl;

        public static ExhibitionResponse of(Exhibition exhibition) {
            return ExhibitionResponse.builder()
                    .exhibitionId(exhibition.getExhibitionId())
                    .thumbnailUrl(exhibition.getThumbnailUrl())
                    .build();
        }

        public static List<ExhibitionResponse> of(List<Exhibition> exhibitions) {
            return exhibitions.stream()
                    .map(ExhibitionResponse::of)
                    .collect(Collectors.toList());
        }
    }
}