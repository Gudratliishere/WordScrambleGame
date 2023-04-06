package com.gudratli.wordscramblegame.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest
{
    private String username;
    private String password;
}
