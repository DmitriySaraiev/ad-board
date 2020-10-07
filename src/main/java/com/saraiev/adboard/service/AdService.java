package com.saraiev.adboard.service;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.user.CreateUserApiRequest;
import com.saraiev.adboard.repositiory.AdRepository;
import org.springframework.stereotype.Service;

@Service
public class AdService {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public User create(CreateUserApiRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return null;
    }

}
