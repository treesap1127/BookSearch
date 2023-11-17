package com.core.login.controller;


import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.filter.JwtFilter;
import com.core.login.LoginDto;
import com.core.login.TokenDto;
import com.core.login.service.UserService;
import com.core.utils.IpCheck;
import com.core.utils.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LoginController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService<User> userService;
    private final IpCheck ipCheck;

    @PostMapping("/admin/auth")
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.SC_OK);
    }

    @GetMapping("/admin/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
    	Optional<User> test = userService.getMyUserWithAuthorities();
        return ResponseEntity.ok(test.get());
    }
    
    @GetMapping("/ipCheck")
    public Map<String, Object> checkIp(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 클라이언트의 IP 주소 가져오기
            String clientIp = ipCheck.getClientIp(request);

            // 로컬 PC 여부 확인
            boolean isLocal = ipCheck.isLocalIp(clientIp);

            // 응답 데이터 설정
            response.put("ip", clientIp);
            response.put("ipAllow", isLocal);
        } catch (UnknownHostException e) {
            // 예외 처리
            e.printStackTrace();
            response.put("ipAllow", false);
        }

        return response;
    }
    
    @PostMapping("/admin/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        // 현재 사용자의 토큰을 얻어온 후 무효화
        tokenProvider.invalidateToken(token);

        return ResponseEntity.ok("로그아웃 성공");
    }
    
    @GetMapping("/jwtCheck")
    public String checkJwtValidity(@RequestParam String jwt) {
        if (tokenProvider.isValidJwt(jwt)) {
            return "true";
        } else {
            return "false";
        }
    }
}
