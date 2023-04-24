package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.dto.response.UserRankResponse;
import com.gudratli.wordscramblegame.entity.User;

import java.util.List;

public interface UserService
{
    User add(User user);

    User getByUsername(String username);

    void decreasePoint(String username, Integer point);

    void increasePoint(String username, Integer point);

    UserRankResponse getUserRankByUsername(String username);

    List<UserRankResponse> getTopUserRank();
}
