package com.gudratli.wordscramblegame.controller;

import com.gudratli.wordscramblegame.dto.request.ChangePointRequest;
import com.gudratli.wordscramblegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/decreasePoint")
    public ResponseEntity<?> decreasePoint (@RequestBody ChangePointRequest changePointRequest)
    {
        userService.decreasePoint(changePointRequest.getId(), changePointRequest.getPoint());

        return ResponseEntity.ok(null);
    }

    @PostMapping("/increasePoint")
    public ResponseEntity<?> increasePoint (@RequestBody ChangePointRequest changePointRequest)
    {
        userService.increasePoint(changePointRequest.getId(), changePointRequest.getPoint());

        return ResponseEntity.ok(null);
    }
}
