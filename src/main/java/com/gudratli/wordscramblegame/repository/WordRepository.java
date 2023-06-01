package com.gudratli.wordscramblegame.repository;

import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.repository.custom.WordRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryCustom
{
    Boolean existsByWordIdAndName (Long id, String name);
}
