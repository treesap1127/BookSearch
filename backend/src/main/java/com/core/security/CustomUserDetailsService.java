package com.core.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
    	if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("{noop}admin") // 인코딩 미사용
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException(username + " -> 사용자를 찾을 수 없습니다.");
        }
    }
}