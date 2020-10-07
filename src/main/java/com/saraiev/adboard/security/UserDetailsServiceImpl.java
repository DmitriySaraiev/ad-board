package com.saraiev.adboard.security;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);
        return new UserDetailsImpl(user);
    }

}
