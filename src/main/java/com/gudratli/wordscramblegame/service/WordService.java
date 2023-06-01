package com.gudratli.wordscramblegame.service;

import com.gudratli.wordscramblegame.entity.Word;

import java.util.List;

public interface WordService
{
    Word getRandomShuffledWord();

    Boolean checkWord (Long id, String word);

    void add (List<Word> words);
}
