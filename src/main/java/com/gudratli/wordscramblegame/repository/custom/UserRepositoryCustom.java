package com.gudratli.wordscramblegame.repository.custom;

public interface UserRepositoryCustom
{
    void decreasePoint (Long userId, Integer point);
    void increasePoint (Long userId, Integer point);
}
