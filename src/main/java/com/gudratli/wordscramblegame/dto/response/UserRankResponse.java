package com.gudratli.wordscramblegame.dto.response;

import lombok.Data;

@Data
public class UserRankResponse
{
    private Integer rank;
    private String username;
    private Integer point;
}
