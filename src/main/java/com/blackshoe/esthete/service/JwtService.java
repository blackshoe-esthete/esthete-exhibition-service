package com.blackshoe.esthete.service;

import io.jsonwebtoken.security.SignatureException;

import java.util.UUID;

public interface JwtService {

    UUID extractUserId(String token) throws SignatureException;
}