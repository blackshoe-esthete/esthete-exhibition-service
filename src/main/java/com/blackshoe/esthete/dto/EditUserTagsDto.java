package com.blackshoe.esthete.dto;

import com.blackshoe.esthete.entity.ExhibitionTag;
import com.blackshoe.esthete.entity.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class EditUserTagsDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TagList {
        private List<String> tags;

        public static TagList of(List<Tag> tags) {
            List<String> tagNames = tags.stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList());
            return new TagList(tagNames);
        }

        public static List<String> ofExhibitionTags(List<ExhibitionTag> exhibitionTags) {
            return exhibitionTags.stream()
                    .map(et -> et.getTag().getName())
                    .collect(Collectors.toList());
        }
    }
}