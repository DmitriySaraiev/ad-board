package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.user.CreateUserApiRequest;
import com.saraiev.adboard.service.AdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    DataApiResponse<Ad> create(@RequestBody @Valid CreateUserApiRequest request) {
        return null;
    }

}
