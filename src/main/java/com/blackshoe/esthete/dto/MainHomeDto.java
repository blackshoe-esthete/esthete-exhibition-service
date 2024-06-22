package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.Comment;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.Photo;
import com.blackshoe.esthete.entity.User;
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

public class MainHomeDto {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionDetailResponse {
        private String title;
        private String date;
        private String author;
        private String authorProfileUrl;
        private String thumbnailUrl;
        private List<PhotoResponse> photos;

        public static ExhibitionDetailResponse of(Exhibition exhibition) {
            return ExhibitionDetailResponse.builder()
                    .title(exhibition.getTitle())
                    .date(exhibition.getCreatedAt().format(DATE_FORMATTER))
                    .author(exhibition.getUser().getNickname())
                    .authorProfileUrl(exhibition.getUser().getProfileUrl().getCloudfrontUrl())
                    .thumbnailUrl(exhibition.getThumbnailUrl())
                    .photos(PhotoResponse.of(exhibition.getPhotos()))
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PhotoResponse {
        private UUID photoId;
        private UUID filterId;
        private Float grayScale;
        private String photoUrl;

        public static PhotoResponse of(Photo photo) {
            return PhotoResponse.builder()
                    .photoId(photo.getPhotoId())
                    .filterId(photo.getFilterId())
                    .grayScale(photo.getGrayScale())
                    .photoUrl(photo.getPhotoUrl().getCloudfrontUrl())
                    .build();
        }

        public static List<PhotoResponse> of(List<Photo> photos) {
            return photos.stream()
                    .map(MainHomeDto.PhotoResponse::of)
                    .collect(Collectors.toList());
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CommentRequest {
        private String exhibitionId;
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CommentResponse {
        private String commentId;
        private String name;
        private String date;
        private String profileUrl;
        private String content;
        private Boolean isLike;

        public static CommentResponse of(Comment comment, User user) {
            return CommentResponse.builder()
                    .commentId(String.valueOf(comment.getCommentId()))
                    .name(user.getNickname())
                    .date(comment.getCreatedAt().format(DATE_FORMATTER))
                    .profileUrl(user.getProfileUrl().getCloudfrontUrl())
                    .content(comment.getContent())
                    .isLike(comment.getIsLike())
                    .build();
        }
    }
}