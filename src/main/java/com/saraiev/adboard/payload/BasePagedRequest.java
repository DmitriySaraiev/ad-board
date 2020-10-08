package com.saraiev.adboard.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class BasePagedRequest {

    @JsonProperty("page")
    Long page = 0L;

    @JsonProperty("size")
    Integer size = 10;

    @JsonProperty("keyword")
    private String keyword = "";

    @JsonProperty("sort_direction")
    String sortDirection = "DESC";
}
