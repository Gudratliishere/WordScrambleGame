package com.gudratli.wordscramblegame.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService
{
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    Boolean isTokenValid(String token, UserDetails userDetails);
}
