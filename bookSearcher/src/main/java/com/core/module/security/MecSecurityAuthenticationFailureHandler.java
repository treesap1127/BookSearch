package com.core.module.security;

import net.mayeye.core.contants.Message;
import net.mayeye.core.module.accnt.MecAccntVo;
import net.mayeye.core.module.accnt.service.MecAccntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * MecSecurityAuthenticationFailureHandler.java
 * MecSecurityAuthenticationFailureHandler
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-08-12         PJY      최초작성
 * ==============================================
 */

public class MecSecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private MecAccntService accntService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String securityLoginUserName = (String) request.getAttribute("SECURITY_LOGIN_USER_NAME");
        accntService.updateLoginFailCount(securityLoginUserName);
        request.setAttribute("message", Message.NOT_CORRECTED_ID_OR_PWD.getMsg());
        request.setAttribute("forward", "/mec/login");
        request.getRequestDispatcher("/message").forward(request, response);
    }

}