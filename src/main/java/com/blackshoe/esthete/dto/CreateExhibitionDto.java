package com.blackshoe.esthete.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateExhibitionDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FilterPhoto {
        private Float grayScale;
        private UUID filterId;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FilterPhotoList {
        private List<FilterPhoto> filterPhotos;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TagList {
        private List<UUID> tags;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionLocation {
        private Double longitude;
        private Double latitude;
        private String state;
        private String city;
        private String town;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CreateExhibitionRequest {

        @NotNull(message = "filter_photo_list를 입력해주세요.")
        private FilterPhotoList filterPhotoList;

        @NotNull(message = "exhibition_information를 입력해주세요.")
        private ExhibitionInformation exhibitionInformation;

        @NotNull(message = "exhibition_location를 입력해주세요.")
        private ExhibitionLocation exhibitionLocation;

        @NotNull(message = "tmp_exhibition_id를 입력해주세요.")
        private UUID tmpExhibitionId;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionInformation {
        private String title;
        private String description;
        private TagList tagList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateTmpExhibitionResponse {
        private UUID tmpExhibitionId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateExhibitionResponse {
        private UUID exhibitionId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionPhotoImgUrl {
        private String cloudfrontUrl;
        private String s3Url;
    }
}
