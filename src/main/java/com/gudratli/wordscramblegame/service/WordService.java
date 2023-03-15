package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.entity.Word;

public interface WordService
{
    Word getRandomShuffledWord();

    Boolean checkWord (Long id, String word);
}
