package com.saraiev.adboard.payload.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;

}
