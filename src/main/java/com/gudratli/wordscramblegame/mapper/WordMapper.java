package com.gudratli.wordscramblegame.mapper;

import com.gudratli.wordscramblegame.dto.response.WordResponse;
import com.gudratli.wordscramblegame.entity.Word;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordMapper
{
    private final ModelMapper mapper;

    public WordResponse map (Word word)
    {
        return mapper.map(word, WordResponse.class);
    }
}
