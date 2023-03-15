package com.gudratli.wordscramblegame.service.impl;

import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.repository.WordRepository;
import com.gudratli.wordscramblegame.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService
{
    private final WordRepository wordRepository;

    @Override
    public Word getRandomShuffledWord()
    {
        List<Word> wordList = wordRepository.findAll();
        Word word = wordList.size() > 0 ? wordList.get(0) : new Word();
        word.setName(shuffleString(word.getName()));
        return word;
    }

    @Override
    public Boolean checkWord(Long id, String word)
    {
        return wordRepository.existsByWordIdAndName(id, word);
    }

    private String shuffleString (String word)
    {
        List<Character> characters = new ArrayList<>();
        for (char c: word.toCharArray())
            characters.add(c);

        StringBuilder stringBuilder = new StringBuilder(word.length());
        while (characters.size() > 0)
        {
            int random = (int) (Math.random() * characters.size());
            stringBuilder.append(characters.remove(random));
        }
        return stringBuilder.toString();
    }
}
