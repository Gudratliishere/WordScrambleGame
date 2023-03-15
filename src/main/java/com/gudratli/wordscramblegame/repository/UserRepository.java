package com.gudratli.wordscramblegame.repository;

import com.gudratli.wordscramblegame.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername (String username);
}
