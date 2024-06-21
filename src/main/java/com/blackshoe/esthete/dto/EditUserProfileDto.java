package com.blackshoe.esthete.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

public class EditUserProfileDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EditUserProfileImgResponse {
        private String s3Url;
        private String cloudfrontUrl;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EditUserProfileInfosRequest {
        private String userName;
        private String userIntroduce;
        private String userBiography;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EditUserProfileInfosResponse {
        private String userName;
        private String userIntroduce;
        private String userBiography;
    }
}