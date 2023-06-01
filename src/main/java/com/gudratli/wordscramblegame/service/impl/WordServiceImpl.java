package com.gudratli.wordscramblegame.service.impl;

import com.gudratli.wordscramblegame.entity.UserWord;
import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.repository.UserRepository;
import com.gudratli.wordscramblegame.repository.UserWordRepository;
import com.gudratli.wordscramblegame.repository.WordRepository;
import com.gudratli.wordscramblegame.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService
{
    private final WordRepository wordRepository;
    private final UserWordRepository userWordRepository;
    private final UserRepository userRepository;

    @Override
    public Word getRandomShuffledWord ()
    {
        Word word = wordRepository.getRandomWord();
        word.setName(shuffleString(word.getName()));

        return word;
    }

    @Override
    public Boolean checkWord (Long id, String word)
    {
        Boolean result = wordRepository.existsByWordIdAndName(id, word);
        if (result)
        {
            UserWord userWord = UserWord.builder()
                    .user(userRepository.findByUsername(getUsername()))
                    .word(wordRepository.findById(id).orElse(null))
                    .build();
            userWordRepository.save(userWord);
        }

        return result;
    }

    @Override
    public void add (List<Word> words)
    {
        wordRepository.saveAll(words);
    }

    private String shuffleString (String word)
    {
        List<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray())
            characters.add(c);

        StringBuilder stringBuilder = new StringBuilder(word.length());
        while (characters.size() > 0)
        {
            int random = (int) (Math.random() * characters.size());
            stringBuilder.append(characters.remove(random));
        }
        return stringBuilder.toString();
    }


    private String getUsername ()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        else
            return principal.toString();
    }
}
