package com.core.login.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl<T> implements UserService{
	
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
    	// 임시 데이터로 권한 정보(authorities) 생성
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        User user = new User("admin", "admin", authorities);

        return Optional.of(user);
    }

}
