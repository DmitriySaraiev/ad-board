package com.saraiev.adboard.controller;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.payload.BaseApiResponse;
import com.saraiev.adboard.payload.CommonResponses;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.PagedData;
import com.saraiev.adboard.payload.ad.AdFilterRequest;
import com.saraiev.adboard.payload.ad.CreateAdApiRequest;
import com.saraiev.adboard.payload.ad.UpdateAdApiRequest;
import com.saraiev.adboard.service.AdService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    DataApiResponse<Ad> get(@RequestParam("id") Long id) {
        return new DataApiResponse<>(adService.get(id));
    }

    @PostMapping("/search")
    DataApiResponse<PagedData<Ad>> search(@RequestBody @Valid AdFilterRequest request) {
        return adService.search(request);
    }

    @PostMapping
    DataApiResponse<Ad> create(@RequestBody @Valid CreateAdApiRequest request, HttpServletRequest servletRequest) {
        Ad ad = adService.create(request, servletRequest);
        return new DataApiResponse<>(ad);
    }

    @PutMapping
    DataApiResponse<Ad> update(@RequestBody @Valid UpdateAdApiRequest request, HttpServletRequest servletRequest) {
        return new DataApiResponse<>(adService.update(request, servletRequest));
    }

    @DeleteMapping
    BaseApiResponse delete(@RequestParam("id") Long id, HttpServletRequest servletRequest) {
        adService.delete(id, servletRequest);
        return CommonResponses.successResponse();
    }

}
