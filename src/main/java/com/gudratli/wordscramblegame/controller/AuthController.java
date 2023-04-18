package com.gudratli.wordscramblegame.controller;

import com.gudratli.wordscramblegame.dto.request.CreateUserRequest;
import com.gudratli.wordscramblegame.dto.request.LoginUserRequest;
import com.gudratli.wordscramblegame.dto.response.JwtResponse;
import com.gudratli.wordscramblegame.dto.response.UserResponse;
import com.gudratli.wordscramblegame.entity.User;
import com.gudratli.wordscramblegame.mapper.UserMapper;
import com.gudratli.wordscramblegame.service.JwtService;
import com.gudratli.wordscramblegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController
{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest createUserRequest)
    {
        User user = userMapper.map(createUserRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.add(user);

        return ResponseEntity.ok(userMapper.map(user));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginUserRequest loginUserRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserRequest.getUsername(), loginUserRequest.getPassword()));
        User user = userService.getByUsername(loginUserRequest.getUsername());
        return ResponseEntity.ok(JwtResponse.builder().token(jwtService.generateToken(user)).build());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Boolean> usernameExist(@PathVariable String username)
    {
        User user = userService.getByUsername(username);
        if (user == null)
            return ResponseEntity.ok(false);
        return ResponseEntity.ok(true);
    }
}
