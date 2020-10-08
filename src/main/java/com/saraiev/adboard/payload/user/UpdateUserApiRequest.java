package com.saraiev.adboard.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saraiev.adboard.domain.Roles;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateUserApiRequest {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("roles")
    private List<String> roles = new ArrayList<>();

}
