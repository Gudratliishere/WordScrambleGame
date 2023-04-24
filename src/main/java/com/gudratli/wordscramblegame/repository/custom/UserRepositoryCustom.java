package com.gudratli.wordscramblegame.repository.custom;

import com.gudratli.wordscramblegame.dto.response.UserRankResponse;

import java.util.List;

public interface UserRepositoryCustom
{
    void decreasePoint(String username, Integer point);

    void increasePoint(String username, Integer point);

    UserRankResponse getUserRankByUsername(String username);

    List<UserRankResponse> getTopUserRank();
}
