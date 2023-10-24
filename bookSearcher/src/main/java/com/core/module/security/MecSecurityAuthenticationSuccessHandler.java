package com.core.module.security;

import net.mayeye.core.contants.MecWhilteList;
import net.mayeye.core.module.accnt.MecAccntVo;
import net.mayeye.core.module.accnt.service.MecAccntService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * MecSecurityAuthenticationSuccessHandler.java
 * 로그인 성공시 추가작업
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-08-12         PJY      최초작성
 * ==============================================
 */

public class MecSecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MecAccntService accntService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        sendRedirect(httpServletRequest,httpServletResponse, authentication);
    }

    private void sendRedirect(HttpServletRequest httpServletRequest,HttpServletResponse response, Authentication authentication) throws IOException {
        MecSecurityUserDetailsAdapter userDetailsAdapter = (MecSecurityUserDetailsAdapter) authentication.getPrincipal();
        MecAccntVo userDetails = userDetailsAdapter.getMecAccntVo();

        /* 로그인 성공시 실패 횟수 초기화 */
        if (userDetailsAdapter.isEnabled()) {
            accntService.resetLoginFailCount(userDetails.getAdmCode());
        }

        /* 비밀번호 변경일이 3개월이 지났는지 확인 */
        Date pwdDate = userDetails.getPwdDate();
        LocalDateTime afterMonthDateTime = null;
        LocalDateTime nowDateTime = null;
        if(pwdDate != null) {
            LocalDateTime changedDate = LocalDateTime.fromDateFields(pwdDate);
            afterMonthDateTime = changedDate.plusMonths(3);
            nowDateTime = LocalDateTime.now();
        }

        /* 조건에 따라 sendRedirect */
        if (nowDateTime != null && nowDateTime.isAfter(afterMonthDateTime)) {
            response.sendRedirect(httpServletRequest.getContextPath()+"/mec/pwd?message=expired");
        } else if (!MecWhilteList.WHITE_ACCNT_LIST.contains(userDetails.getAdmId())) {
            response.sendRedirect(httpServletRequest.getContextPath()+"/mec/article/master?mno=1&init=info");
        } else {
            response.sendRedirect(httpServletRequest.getContextPath()+"/mec?init=info");
        }
    }
}
