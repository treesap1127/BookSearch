package com.core.module.security;

import org.springframework.security.core.context.SecurityContextHolder;

import net.mayeye.core.module.accnt.MecAccntVo;

/**
 * MecSecurityUtils.java
 * MecSecurityUtils
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-04-10         PJY      최초작성
 * ==============================================
 */
public class MecSecurityUtils {

    /**
     * 시큐리티 로그인한 관리자 Vo를 가져온다
     * @return MecAccntVo
     */
    public static MecAccntVo getAccnt() {
        MecSecurityUserDetailsAdapter userDetailsAdapter = (MecSecurityUserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailsAdapter.getMecAccntVo();
    }

}
