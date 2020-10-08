package com.saraiev.adboard.service;

import com.saraiev.adboard.domain.Roles;
import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.error.CustomApiException;
import com.saraiev.adboard.error.UnauthorizedException;
import com.saraiev.adboard.payload.auth.RegisterRequest;
import com.saraiev.adboard.payload.user.UpdateUserApiRequest;
import com.saraiev.adboard.repositiory.RoleRepository;
import com.saraiev.adboard.repositiory.UserRepository;
import com.saraiev.adboard.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RequestUtils requestUtils;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByUsername(String login) {
        try {
            return userRepository.getByLogin(login);
        } catch (NoResultException e) {
            throw new CustomApiException("No such user");
        }
    }

    public User getByLoginAndPassword(String login, String password) {
        try {
            User user = userRepository.getByLogin(login);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new UnauthorizedException("Password is wrong");
            }
        } catch (NoResultException e) {
            throw new CustomApiException("No such user");
        }
    }

    public User registerNewUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBio(request.getBio());
        user.getRoles().add(roleRepository.getByName(Roles.USER.getName()));
        return userRepository.save(user);
    }

    public User update(UpdateUserApiRequest request, HttpServletRequest servletRequest) {
        User editorUser = userRepository.getById(requestUtils.getUserId(servletRequest));
        User user = userRepository.getById(request.getId());
        user.setBio(request.getBio());
        if (editorUser.getRoles().contains(roleRepository.getByName(Roles.ADMIN.getName()))) {
            user.getRoles().clear();
            request.getRoles().forEach(
                    role -> user.getRoles().add(roleRepository.getByName(role))
            );
        }
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return getByUsername(login);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

}
