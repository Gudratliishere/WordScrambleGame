package com.gudratli.wordscramblegame.repository;

import com.gudratli.wordscramblegame.entity.User;
import com.gudratli.wordscramblegame.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom
{
    User findByUsername (String username);
}
