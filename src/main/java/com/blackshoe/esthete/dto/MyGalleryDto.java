package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemporaryExhibitionDetailResponse {
        private String title;
        private String description;
        private List<PhotoResponse> photos;
        private List<String> tags;
        @JsonProperty("location")
        private LocationResponse locationResponse;

        public static TemporaryExhibitionDetailResponse of(TemporaryExhibition temporaryExhibition) {
            return TemporaryExhibitionDetailResponse.builder()
                    .title(temporaryExhibition.getTitle())
                    .description(temporaryExhibition.getDescription())
                    .photos(PhotoResponse.of(temporaryExhibition.getPhotos()))
                    .tags(EditUserTagsDto.TagList.ofExhibitionTags(temporaryExhibition.getExhibitionTags()))
                    .locationResponse(LocationResponse.of(temporaryExhibition.getExhibitionLocation()))
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

        public static PhotoResponse of(Photo photo) {
            return PhotoResponse.builder()
                    .photoId(photo.getPhotoId())
                    .filterId(photo.getFilterId())
                    .grayScale(photo.getGrayScale())
                    .build();
        }

        public static List<PhotoResponse> of(List<Photo> photos) {
            return photos.stream()
                    .map(PhotoResponse::of)
                    .collect(Collectors.toList());
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LocationResponse {
        private Double longitude;
        private Double latitude;
        private String state;
        private String city;
        private String town;

        public static LocationResponse of(ExhibitionLocation exhibitionLocation) {
            return LocationResponse.builder()
                    .longitude(exhibitionLocation.getLongitude())
                    .latitude(exhibitionLocation.getLatitude())
                    .state(exhibitionLocation.getState())
                    .city(exhibitionLocation.getCity())
                    .town(exhibitionLocation.getTown())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AuthorIntroductionResponse {
        private String name;
        private String introduce;
        private String biography;
        private String profileUrl;
        private Long followerCount;
        private Long followingCount;
        private Boolean isFollowed;

        public static AuthorIntroductionResponse of(User user) {
            return AuthorIntroductionResponse.builder()
                    .name(user.getNickname())
                    .introduce(user.getIntroduce())
                    .biography(user.getBiography())
                    .profileUrl(user.getProfileUrl().getCloudfrontUrl())
                    .followerCount(user.getFollowerCount())
                    .followingCount(user.getFollowingCount())
                    .build();
        }

        public void updateFollow(boolean isFollowed) {
            this.isFollowed = isFollowed;
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExhibitionResponse {
        private UUID exhibitionId;
        private String thumbnailUrl;
        private String title;
        private String date;
        private Boolean isLiked;

        public static ExhibitionResponse of(Exhibition exhibition, boolean isLiked) {
            return ExhibitionResponse.builder()
                    .exhibitionId(exhibition.getExhibitionId())
                    .thumbnailUrl(exhibition.getThumbnailUrl())
                    .title(exhibition.getTitle())
                    .date(exhibition.getCreatedAt().format(DATE_FORMATTER))
                    .isLiked(isLiked)
                    .build();
        }

        public static ExhibitionResponse of(Exhibition exhibition) {
            return of(exhibition, false);
        }

        public static List<ExhibitionResponse> of(List<Exhibition> exhibitions, List<Like> likes) {
            return exhibitions.stream()
                    .map(exhibition -> {
                        boolean isLiked = likes.stream()
                                .anyMatch(like -> like.getExhibitionId().equals(exhibition.getExhibitionId()));
                        return ExhibitionResponse.of(exhibition, isLiked);
                    })
                    .collect(Collectors.toList());
        }
    }
}