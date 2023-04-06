package com.gudratli.wordscramblegame.mapper;

import com.gudratli.wordscramblegame.dto.request.CreateUserRequest;
import com.gudratli.wordscramblegame.dto.response.UserResponse;
import com.gudratli.wordscramblegame.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper
{
    private final ModelMapper modelMapper;

    public User map(CreateUserRequest createUserRequest)
    {
        return modelMapper.map(createUserRequest, User.class);
    }

    public UserResponse map(User user)
    {
        return modelMapper.map(user, UserResponse.class);
    }
}
