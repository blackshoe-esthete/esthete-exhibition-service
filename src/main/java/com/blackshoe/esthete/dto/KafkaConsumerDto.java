package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.common.constant.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


public class KafkaConsumerDto {

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserCreate {
        private UUID userId;
        private String nickname;
        private String email;
        private Gender gender;
        private LocalDate birthday;

        @Builder
        public UserCreate(UUID userId, String nickname, String email, Gender gender, LocalDate birthday) {
            this.userId = userId;
            this.nickname = nickname;
            this.email = email;
            this.gender = gender;
            this.birthday = birthday;
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DeletePhoto {
        private String photoId;

        @Builder
        public DeletePhoto(String photoId) {
            this.photoId = photoId;
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DeleteComment {
        private String commentId;

        @Builder
        public DeleteComment(String commentId) {
            this.commentId = commentId;
        }
    }

}
