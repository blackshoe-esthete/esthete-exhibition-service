package com.blackshoe.esthete.service;

public interface GeoCodingService {
    String getCoordinateFromAddress(String address);

    String getAddressFromCoordinate(Double latitude, Double longitude);
}
