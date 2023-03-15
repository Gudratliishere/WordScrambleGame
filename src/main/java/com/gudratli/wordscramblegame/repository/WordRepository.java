package com.gudratli.wordscramblegame.repository;

import com.gudratli.wordscramblegame.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long>
{
    Boolean existsByWordIdAndName (Long id, String name);
}
