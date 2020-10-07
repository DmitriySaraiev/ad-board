package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.auth.AuthRequest;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.security.JwtProvider;
import com.saraiev.adboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public DataApiResponse<String> auth(@RequestBody AuthRequest request) {
        User user = userService.getByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new DataApiResponse<>(token);
    }


    @PostMapping("/register")
    public DataApiResponse<String> register(@RequestBody AuthRequest request) {
        User user = userService.getByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new DataApiResponse<>(token);
    }

}
