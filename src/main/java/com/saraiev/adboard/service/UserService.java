package com.saraiev.adboard.service;

import com.saraiev.adboard.domain.Role;
import com.saraiev.adboard.domain.Roles;
import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.payload.user.CreateUserApiRequest;
import com.saraiev.adboard.repositiory.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository/*, PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public User getByLoginAndPassword(String login, String password) {
        return userRepository.getByLogin(login);
    }

    public User create(CreateUserApiRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.getRoles().add(new Role(Roles.USER.getName()));
        user.setPassword(request.getPassword());
        return userRepository.create(user);
    }

}
