package com.gudratli.wordscramblegame.service.impl;

import com.gudratli.wordscramblegame.entity.User;
import com.gudratli.wordscramblegame.repository.UserRepository;
import com.gudratli.wordscramblegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Override
    public User add(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
}
