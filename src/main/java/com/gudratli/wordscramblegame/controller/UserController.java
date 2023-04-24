package com.gudratli.wordscramblegame.controller;

import com.gudratli.wordscramblegame.dto.response.UserRankResponse;
import com.gudratli.wordscramblegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/decreasePoint/{point}")
    public ResponseEntity<?> decreasePoint(@PathVariable Integer point)
    {
        userService.decreasePoint(getUsername(), point);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/increasePoint/{point}")
    public ResponseEntity<?> increasePoint(@PathVariable Integer point)
    {
        userService.increasePoint(getUsername(), point);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/rank")
    public ResponseEntity<UserRankResponse> getRank()
    {
        String username = getUsername();
        UserRankResponse userRankResponse = userService.getUserRankByUsername(username);

        return ResponseEntity.ok(userRankResponse);
    }

    @GetMapping("/playersRank")
    public ResponseEntity<List<UserRankResponse>> getPlayersRank()
    {
        List<UserRankResponse> userRankResponseList = userService.getTopUserRank();

        return ResponseEntity.ok(userRankResponseList);
    }

    private String getUsername()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        else
            return principal.toString();
    }
}
