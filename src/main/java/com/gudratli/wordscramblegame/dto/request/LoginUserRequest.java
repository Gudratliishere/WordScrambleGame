package com.gudratli.wordscramblegame.dto.request;

import lombok.Data;

@Data
public class LoginUserRequest
{
    private String username;
    private String password;
}
