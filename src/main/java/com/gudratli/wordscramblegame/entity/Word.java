package com.gudratli.wordscramblegame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "words")
public class Word
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    @Column(columnDefinition = "nvarchar(500)")
    private String hint;
}
