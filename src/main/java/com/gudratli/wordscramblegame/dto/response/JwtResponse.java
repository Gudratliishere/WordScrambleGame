package com.gudratli.wordscramblegame.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse
{
    private String token;
}
