package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.entity.User;

public interface UserService
{
    User add(User user);

    User getByUsername(String username);

    void decreasePoint(Long userId, Integer point);

    void increasePoint(Long userId, Integer point);
}
