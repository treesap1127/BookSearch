package com.core.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
    	sendErrorRespons(response,"인증 실패");
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    private void sendErrorRespons(HttpServletResponse response, String message) throws IOException {
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String jsonResponse = "{\"message\": \"" + message + "\"}";
        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
	}
}
