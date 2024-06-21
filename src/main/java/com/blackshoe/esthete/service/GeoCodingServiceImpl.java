package com.blackshoe.esthete.service;

import com.blackshoe.esthete.exception.GeoCodingApiErrorResult;
import com.blackshoe.esthete.exception.GeoCodingApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoCodingServiceImpl implements GeoCodingService{

    @Value("${services.geo-coding-service}")
    private String GEO_CODING_SERVICE_URL;

    @Value("${spring.cloud.gcp.geo-coding.api-key}")
    private String GEO_CODING_SERVICE_API_KEY;

    @Override
    public String getCoordinateFromAddress(String address) {

        WebClient webClient = WebClient.builder()
                .baseUrl(GEO_CODING_SERVICE_URL)
                .build();

        String coordinateFromAddress = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/json")
                        .queryParam("address", address)
                        .queryParam("key", GEO_CODING_SERVICE_API_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    log.error("geo-coding-service 4xx error");
                    throw new GeoCodingApiException(GeoCodingApiErrorResult.GEO_CODING_SERVICE_4XX_ERROR);
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.error("geo-coding-service 5xx error");
                    throw new GeoCodingApiException(GeoCodingApiErrorResult.GEO_CODING_SERVICE_5XX_ERROR);
                })
                .bodyToMono(String.class)
                .block();

        return coordinateFromAddress;
    }


    @Override
    public String getAddressFromCoordinate(Double latitude, Double longitude) {

        WebClient webClient = WebClient.builder()
                .baseUrl(GEO_CODING_SERVICE_URL)
                .build();

        String addrFromCoordinate = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/json")
                        .queryParam("latlng", latitude + "," + longitude)
                        .queryParam("key", GEO_CODING_SERVICE_API_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    log.error("geo-coding-service 4xx error");
                    throw new GeoCodingApiException(GeoCodingApiErrorResult.GEO_CODING_SERVICE_4XX_ERROR);
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.error("geo-coding-service 5xx error");
                    throw new GeoCodingApiException(GeoCodingApiErrorResult.GEO_CODING_SERVICE_5XX_ERROR);
                })
                .bodyToMono(String.class)
                .block();

        return addrFromCoordinate;
    }

}
