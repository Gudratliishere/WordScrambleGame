package com.gudratli.wordscramblegame.mapper;

import com.gudratli.wordscramblegame.dto.request.CreateWordRequest;
import com.gudratli.wordscramblegame.dto.response.WordResponse;
import com.gudratli.wordscramblegame.entity.Word;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordMapper
{
    private final ModelMapper mapper;

    public WordResponse map (Word word)
    {
        return mapper.map(word, WordResponse.class);
    }

    public List<Word> map (List<CreateWordRequest> source)
    {
        List<Word> target = new ArrayList<>();
        source.forEach(word -> target.add(map(word)));

        return target;
    }

    public Word map (CreateWordRequest source)
    {
        return mapper.map(source, Word.class);
    }
}
