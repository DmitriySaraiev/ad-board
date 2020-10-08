package com.saraiev.adboard.utils;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.error.UnauthorizedException;
import com.saraiev.adboard.security.JwtProvider;
import com.saraiev.adboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class RequestUtils {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;
    private final UserService userService;

    public RequestUtils(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    private String getAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new UnauthorizedException("Authorization error");
        }
        return authorization;
    }

    public Long getUserId(HttpServletRequest request) {
        String authorizationString = getAuthorization(request);

        if (authorizationString.startsWith(TOKEN_PREFIX)) {
            String username = jwtProvider.decodeJWT(StringUtils.substringAfter(authorizationString, TOKEN_PREFIX)).get("sub", String.class);
            User user = userService.getByUsername(username);
            return user.getId();
        }
        throw new UnauthorizedException("Authorization error");
    }

}
