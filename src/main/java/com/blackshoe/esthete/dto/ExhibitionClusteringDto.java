package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.Exhibition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ExhibitionClusteringDto {
    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MarkedRegionGroupResponse { // 대표 클러스터 마크
        private Long count;
        private String state;
        private String city;
        private String town;
        private String thumbnail;

        @Builder
        public MarkedRegionGroupResponse(Long count, String state, String city, String town, String thumbnail){
            this.count = count;
            this.state = state;
            this.city = city;
            this.town = town;
            this.thumbnail = thumbnail;
        }

        @Builder
        public MarkedRegionGroupResponse(Long count, String state, String city, String thumbnail){
            this.count = count;
            this.state = state;
            this.city = city;
            this.thumbnail = thumbnail;
        }

        @Builder
        public MarkedRegionGroupResponse(Long count, String state, String thumbnail){
            this.count = count;
            this.state = state;
            this.thumbnail = thumbnail;
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class MarkedExhibitionsResponse {
        private UUID exhibitionId;
        private String title;
        private String thumbnailUrl;

        public MarkedExhibitionsResponse(Exhibition exhibition){
            this.exhibitionId = exhibition.getExhibitionId();
            this.title = exhibition.getTitle();
            this.thumbnailUrl = exhibition.getThumbnailUrl();
        }
    }

}
