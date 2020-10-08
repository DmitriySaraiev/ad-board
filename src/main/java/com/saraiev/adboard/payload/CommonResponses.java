package com.saraiev.adboard.payload;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponses {

    public static BaseApiResponse successResponse() {
        return new BaseApiResponse(200, "Success");
    }

}
