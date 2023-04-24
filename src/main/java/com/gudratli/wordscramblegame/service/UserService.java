package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.entity.User;

public interface UserService
{
    User add(User user);

    User getByUsername(String username);

    void decreasePoint(String username, Integer point);

    void increasePoint(String username, Integer point);
}
