package com.blackshoe.esthete.common.vo;

import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ExhibitionSortType {
    TRENDING("exhibition.viewCount"),
    RECENT("exhibition.createdAt");

    private final String sortType;

    public static Sort convertParamToColumn(String sort) {
        switch (sort) {
            case "trending":
                return Sort.by(Sort.Direction.DESC, TRENDING.sortType);
            case "recent":
                return Sort.by(Sort.Direction.DESC, RECENT.sortType);
            default:
                throw new ExhibitionException(ExhibitionErrorResult.INVALID_SORT_TYPE);
        }
    }
}
