package com.blackshoe.esthete.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class KafkaProducerDto {

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserDelete {
        private UUID userId;

        @Builder
        public UserDelete(UUID userId){
            this.userId = userId;
        }
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserProfileImgUrl {
        private UUID userId;
        private String profileImgUrl;

        @Builder
        public UserProfileImgUrl(UUID userId, String profileImgUrl){
            this.userId = userId;
            this.profileImgUrl = profileImgUrl;
        }
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserNickName {
        private UUID userId;
        private String nickname;

        @Builder
        public UserNickName(UUID userId, String nickname){
            this.userId = userId;
            this.nickname = nickname;
        }
    }
}