package com.blackshoe.esthete.controller;


import com.blackshoe.esthete.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test/token")
@RequiredArgsConstructor
public class TestTokenController {
    private final JwtUtil jwtUtil;

    @GetMapping()
    public ResponseEntity<String> test(
            @RequestHeader("Authorization") String authorizationHeader){
        String accessToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        UUID userId = UUID.fromString(jwtUtil.getUserIdFromToken(accessToken));
        return ResponseEntity.ok("sucess");
    }
}
