package com.saraiev.adboard.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saraiev.adboard.payload.BasePagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilterRequest extends BasePagedRequest {

    @JsonProperty("username")
    private String username;

}
