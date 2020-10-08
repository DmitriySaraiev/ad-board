package com.saraiev.adboard.payload.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UpdateAdApiRequest {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Length(max = 500)
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

}
