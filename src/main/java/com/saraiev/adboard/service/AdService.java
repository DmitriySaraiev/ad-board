package com.saraiev.adboard.service;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.domain.Role;
import com.saraiev.adboard.domain.Roles;
import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.error.UnauthorizedException;
import com.saraiev.adboard.payload.DataApiResponse;
import com.saraiev.adboard.payload.PagedData;
import com.saraiev.adboard.payload.ad.AdFilterRequest;
import com.saraiev.adboard.payload.ad.CreateAdApiRequest;
import com.saraiev.adboard.payload.ad.UpdateAdApiRequest;
import com.saraiev.adboard.repositiory.AdRepository;
import com.saraiev.adboard.repositiory.UserRepository;
import com.saraiev.adboard.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    private final RequestUtils requestUtils;

    public AdService(AdRepository adRepository, UserRepository userRepository, RequestUtils requestUtils) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.requestUtils = requestUtils;
    }

    public Ad get(Long id) {
        return adRepository.getById(id);
    }

    public Ad create(CreateAdApiRequest request, HttpServletRequest servletRequest) {
        Long userId = requestUtils.getUserId(servletRequest);
        User authorUser = userRepository.getById(userId);
        Ad ad = new Ad();
        ad.setTitle(request.getTitle());
        ad.setDescription(request.getDescription());
        ad.setUser(authorUser);
        ad.setDatePosted(LocalDateTime.now());
        return adRepository.save(ad);
    }

    public Ad update(UpdateAdApiRequest request, HttpServletRequest servletRequest) {
        Long userId = requestUtils.getUserId(servletRequest);
        User user = userRepository.getById(userId);
        Ad ad = adRepository.getById(request.getId());
        if (userId.equals(ad.getUser().getId()) || hasRole(user.getRoles(), Roles.ADMIN.getName())) {
            ad.setTitle(request.getTitle());
            ad.setDescription(request.getDescription());
            return adRepository.save(ad);
        } else {
            throw new UnauthorizedException("No access to this ad modifying");
        }
    }

    public void delete(Long adId, HttpServletRequest servletRequest) {
        Long userId = requestUtils.getUserId(servletRequest);
        User user = userRepository.getById(userId);
        Ad ad = adRepository.getById(adId);
        if (userId.equals(ad.getUser().getId()) || hasRole(user.getRoles(), Roles.ADMIN.getName())) {
            adRepository.delete(adId);
        } else {
            throw new UnauthorizedException("No access to this ad modifying");
        }
    }

    public DataApiResponse<PagedData<Ad>> search(AdFilterRequest request) {
        return new DataApiResponse<>(PagedData.create(request, adRepository.search(request), adRepository.searchTotalCount(request)));
    }

    private boolean hasRole(Set<Role> roles, String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

}
