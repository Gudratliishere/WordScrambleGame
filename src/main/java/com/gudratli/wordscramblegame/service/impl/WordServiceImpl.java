package com.gudratli.wordscramblegame.service.impl;

import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.repository.WordRepository;
import com.gudratli.wordscramblegame.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService
{
    private final WordRepository wordRepository;

    @Override
    public Word getRandomWord()
    {
        List<Word> wordList = wordRepository.findAll();
        return wordList.size() > 0 ? wordList.get(0) : new Word();
    }
}
