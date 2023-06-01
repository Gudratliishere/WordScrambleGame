package com.gudratli.wordscramblegame.repository;

import com.gudratli.wordscramblegame.entity.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWordRepository extends JpaRepository<UserWord, Long>
{
}
