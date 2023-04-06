package com.gudratli.wordscramblegame.service.impl;

import com.gudratli.wordscramblegame.entity.User;
import com.gudratli.wordscramblegame.repository.UserRepository;
import com.gudratli.wordscramblegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return getByUsername(username);
    }
}
