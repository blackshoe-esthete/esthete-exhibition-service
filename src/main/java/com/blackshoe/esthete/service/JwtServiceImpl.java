package com.blackshoe.esthete.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Override
    public UUID extractUserId(String token) throws SignatureException{
        try {
            token = token.replace("Bearer ", "");

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.get("userId", String.class);
            return UUID.fromString(userId);

        } catch (JwtException e) {
            throw new SignatureException("유효하지 않은 토큰입니다.");
        }
    }
}