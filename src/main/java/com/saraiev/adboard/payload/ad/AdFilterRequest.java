package com.saraiev.adboard.payload.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saraiev.adboard.payload.BasePagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdFilterRequest extends BasePagedRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("date_added_start")
    private LocalDateTime dateAddedStart;

    @JsonProperty("date_added_end")
    private LocalDateTime dateAddedEnd;

    @JsonProperty("sort_field")
    private String sortField = "id";

}
