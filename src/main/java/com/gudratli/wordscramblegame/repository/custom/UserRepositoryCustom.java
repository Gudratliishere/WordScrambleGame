package com.gudratli.wordscramblegame.repository.custom;

public interface UserRepositoryCustom
{
    void decreasePoint (String username, Integer point);
    void increasePoint (String username, Integer point);
}
