package com.blackshoe.esthete.controller;

import com.blackshoe.esthete.dto.CreateExhibitionDto;
import com.blackshoe.esthete.service.AdditionService;
import com.blackshoe.esthete.service.GeoCodingService;
import com.blackshoe.esthete.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/exhibition/addition")
public class AdditionController {
    private final JwtUtil jwtUtil;
    private final AdditionService additionService;
    private final GeoCodingService geoCodingService;

    @PostMapping(value = "/temporary_exhibition", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CreateExhibitionDto.CreateTmpExhibitionResponse> saveTemporaryExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestPart(name = "exhibition_photo") List<MultipartFile> exhibitionPhotos,
            @RequestPart CreateExhibitionDto.CreateExhibitionRequest requestDto){
        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        UUID userId = UUID.fromString(jwtUtil.getUserIdFromToken(accessToken));
        CreateExhibitionDto.CreateTmpExhibitionResponse tmpExhibitionResponse = additionService.saveTemporaryExhibition(userId, exhibitionPhotos, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmpExhibitionResponse);
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CreateExhibitionDto.CreateExhibitionResponse> saveExhibition(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestPart(name = "exhibition_photo") List<MultipartFile> exhibitionPhotos,
            @RequestPart CreateExhibitionDto.CreateExhibitionRequest requestDto){
        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        UUID userId = UUID.fromString(jwtUtil.getUserIdFromToken(accessToken));
        CreateExhibitionDto.CreateExhibitionResponse exhibitionResponse = additionService.saveExhibition(userId, exhibitionPhotos, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exhibitionResponse);
    }

    @GetMapping("/geo-coding")
    public ResponseEntity<String> geoCoding(@RequestParam String address) {
        String coordinateFromAddress = geoCodingService.getCoordinateFromAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(coordinateFromAddress);
    }

    @GetMapping("/reverse-geo-coding")
    public ResponseEntity<String> reverseGeoCoding(@RequestParam Double latitude, @RequestParam Double longitude) {
        String addrFromCoordinate = geoCodingService.getAddressFromCoordinate(latitude, longitude);
        return ResponseEntity.status(HttpStatus.OK).body(addrFromCoordinate);
    }
}
