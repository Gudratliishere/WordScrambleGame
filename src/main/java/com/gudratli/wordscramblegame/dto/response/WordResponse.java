package com.gudratli.wordscramblegame.dto.response;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordResponse
{
    private Long wordId;
    private String name;
    private String hint;
}
