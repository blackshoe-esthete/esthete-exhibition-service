package com.blackshoe.esthete.common.vo;

import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import lombok.Builder;
import lombok.Data;

@Data
public class ExhibitionAddressFilter {
    private String state;
    private String city;
    private String town;

    @Builder
    public ExhibitionAddressFilter(String state, String city, String town){
        this.state = state;
        this.city = city;
        this.town = town;
    }

    public ExhibitionAddressSearchType getSearchType() {
        if (!state.equals("") && !city.equals("") && !town.equals("")) {
            return ExhibitionAddressSearchType.TOWN;
        }
        if (!state.equals("") && !city.equals("")) {
            return ExhibitionAddressSearchType.CITY;
        }
        if (!state.equals("")) {
            return ExhibitionAddressSearchType.STATE;
        }
        throw new ExhibitionException(ExhibitionErrorResult.INVALID_ADDRESS_FILTER);
    }
}
