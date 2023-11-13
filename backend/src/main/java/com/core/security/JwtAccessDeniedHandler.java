package com.core.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
    	sendErrorRespons(response,"권한 부족");
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    private void sendErrorRespons(HttpServletResponse response, String message) throws IOException {
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json"); // JSON 형식으로 응답한다고 가정
        response.setCharacterEncoding("UTF-8");
        
        String jsonResponse = "{\"message\": \"" + message + "\"}";
        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
	}
}