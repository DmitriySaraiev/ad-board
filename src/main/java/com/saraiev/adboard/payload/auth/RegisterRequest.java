package com.saraiev.adboard.payload.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String login;
    private String password;

}
