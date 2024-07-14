package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.common.constant.Gender;
import com.blackshoe.esthete.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

}
