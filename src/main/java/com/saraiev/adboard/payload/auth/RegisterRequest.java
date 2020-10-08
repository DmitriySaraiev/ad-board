package com.saraiev.adboard.payload.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    private String bio;

}
