package com.gudratli.wordscramblegame.dto.request;

import lombok.Data;

@Data
public class CreateWordRequest
{
    private String name;
    private String hint;
}
