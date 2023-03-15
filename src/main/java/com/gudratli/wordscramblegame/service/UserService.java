package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.entity.User;

public interface UserService
{
    User add (User user);

    User getByUsername (String username);
}
