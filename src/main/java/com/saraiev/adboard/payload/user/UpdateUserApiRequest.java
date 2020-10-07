package com.saraiev.adboard.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserApiRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("name")
    private String login;

    @JsonProperty("password")
    private String password;

}
