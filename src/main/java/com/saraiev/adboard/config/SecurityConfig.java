package com.saraiev.adboard.config;

import com.saraiev.adboard.domain.Roles;
import com.saraiev.adboard.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/user").hasAuthority(Roles.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/user").hasAnyAuthority(Roles.ADMIN.getName(), Roles.USER.getName())
                .antMatchers(HttpMethod.GET, "/user").hasAnyAuthority(Roles.ADMIN.getName(), Roles.USER.getName())
                .antMatchers("/ad/*").hasAnyAuthority(Roles.ADMIN.getName(), Roles.USER.getName())
                .antMatchers("/register", "/auth").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

