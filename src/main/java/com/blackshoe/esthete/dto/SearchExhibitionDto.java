package com.blackshoe.esthete.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.UUID;

public class SearchExhibitionDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SearchExhibitionResponse {
        private UUID exhibitionId;
        private String exhibitionTitle;
        private String photographerName;
        private String thumbnailImgUrl;
        private Long viewCount;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SearchAuthorResponse {
        private UUID photographerId;
        private String photographerName;
        private String photographerIntroduction;
        private String profileImgUrl;
        private Long supportCount;
    }
}
