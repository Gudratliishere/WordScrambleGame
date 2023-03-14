package com.gudratli.wordscramblegame.controller;


import com.gudratli.wordscramblegame.dto.response.WordResponse;
import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.mapper.WordMapper;
import com.gudratli.wordscramblegame.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController
{
    private final WordService wordService;
    private final WordMapper wordMapper;

    @GetMapping("/random")
    public ResponseEntity<WordResponse> random ()
    {
        Word word = wordService.getRandomWord();
        WordResponse wordResponse = wordMapper.map(word);
        return ResponseEntity.ok(wordResponse);
    }
}
