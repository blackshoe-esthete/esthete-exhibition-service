package com.blackshoe.esthete.common.vo;

import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExhibitionLocationGroupType {
    STATE("state"),
    CITY("city"),
    TOWN("town");

    private final String locationGroupType;

    public static ExhibitionLocationGroupType convertParamToColumn(String type) {
        switch (type) {
            case "state":
                return ExhibitionLocationGroupType.STATE;
            case "city":
                return ExhibitionLocationGroupType.CITY;
            case "town":
                return ExhibitionLocationGroupType.TOWN;
            default:
                throw new ExhibitionException(ExhibitionErrorResult.INVALID_LOCATION_GROUP_TYPE);
        }
    }
}
