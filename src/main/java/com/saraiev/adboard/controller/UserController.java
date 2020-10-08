package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.BaseApiResponse;
import com.saraiev.adboard.payload.CommonResponses;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.user.UpdateUserApiRequest;
import com.saraiev.adboard.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    DataApiResponse<User> update(@RequestBody @Valid UpdateUserApiRequest request, HttpServletRequest servletRequest) {
        return new DataApiResponse<>(userService.update(request, servletRequest));
    }

    @DeleteMapping
    BaseApiResponse delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return CommonResponses.successResponse();
    }

}
