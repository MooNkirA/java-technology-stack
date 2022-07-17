package com.moon.activiti.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Spring Security 工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-16 17:43
 * @description
 */
@Component
public class SecurityUtil {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    public void logInAs(String username) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
        }
        LOGGER.info("> Logged in as: " + username);

        Authentication authentication = new Authentication() {
            private static final long serialVersionUID = -773994554780044680L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            @Override
            public Object getDetails() {
                return user;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return user.getUsername();
            }
        };

        SecurityContextHolder.setContext(new SecurityContextImpl(authentication));

        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }
}
