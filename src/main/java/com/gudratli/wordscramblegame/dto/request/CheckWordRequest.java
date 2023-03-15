package com.gudratli.wordscramblegame.dto.request;

import lombok.Data;

@Data
public class CheckWordRequest
{
    private Long wordId;
    private String name;
}
