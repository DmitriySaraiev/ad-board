package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.user.CreateUserApiRequest;
import com.saraiev.adboard.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    DataApiResponse<User> create(@RequestBody @Valid CreateUserApiRequest request) {
        return new DataApiResponse<>(userService.create(request));
    }


}
