package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.auth.AuthRequest;
import com.saraiev.adboard.payload.auth.RegisterRequest;
import com.saraiev.adboard.security.JwtProvider;
import com.saraiev.adboard.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AuthController {

    private final JwtProvider jwtProvider;

    private final UserService userService;

    public AuthController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public DataApiResponse<String> auth(@RequestBody @Valid AuthRequest request) {
        User user = userService.getByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new DataApiResponse<>(token);
    }

    @PostMapping("/register")
    public DataApiResponse<User> register(@RequestBody @Valid RegisterRequest request) {
        return new DataApiResponse<>(userService.registerNewUser(request));
    }

}
