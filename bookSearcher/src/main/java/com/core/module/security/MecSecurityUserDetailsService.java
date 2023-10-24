package com.core.module.security;

import lombok.extern.slf4j.Slf4j;
import net.mayeye.core.commons.ShowUserMessageException;
import net.mayeye.core.module.accessLog.MecAccessLogVo;
import net.mayeye.core.module.accessLog.service.MecAccessLogService;
import net.mayeye.core.module.articleRole.MecArticleRoleVo;
import net.mayeye.core.module.articleRole.service.MecArticleRoleService;
import net.mayeye.core.module.contentsRole.MecContentsRoleVo;
import net.mayeye.core.module.contentsRole.service.MecContentsRoleService;
import net.mayeye.core.module.policyIp.MecPolicyIpDto;
import net.mayeye.core.module.policyIp.MecPolicyIpVo;
import net.mayeye.core.module.policyIp.service.MecPolicyIpService;
import net.mayeye.core.module.privacy.service.MecPrivacyService;
import net.mayeye.core.module.roleMenu.service.MecRoleMenuService;
import net.mayeye.core.util.MecForwardedUtils;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mayeye.core.module.accnt.MecAccntVo;
import net.mayeye.core.module.accnt.service.MecAccntService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * MecSecurityUserDetailsService.java
 * MecSecurityUserDetailsService
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-04-10         PJY      최초작성
 * ==============================================
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MecSecurityUserDetailsService implements UserDetailsService {

    private final MecAccntService service;
    private final MecAccessLogService accessLogService;
    private final MecPolicyIpService policyIpService;
    private final MecRoleMenuService roleMenuService;
    private final MecArticleRoleService articleRoleService;
    private final MecContentsRoleService contentsRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MecAccntVo mecAccntVo = new MecAccntVo();
        mecAccntVo.setAdmId(username);
        MecAccntVo accnt = service.findAccnt(mecAccntVo);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestIp = MecForwardedUtils.xForwarded(request);
        loggingAccessLog(accnt, requestIp);
        checkAuthenticateIps(accnt, requestIp);
        request.setAttribute("SECURITY_LOGIN_USER_NAME", accnt.getAdmCode());
        return new MecSecurityUserDetailsAdapter(accnt);
    }


    private void checkAuthenticateIps(MecAccntVo accnt, String requestIp) {
        /*check policy Ips*/
        log.info("Check PolicyIp...");
        MecPolicyIpDto.Login policyIpDto = new MecPolicyIpDto.Login(accnt.getAdmCode());
        List<MecPolicyIpVo> mecPolicyIps = policyIpService.checkPolicyIps(policyIpDto);

        boolean isAllow = false;
        for (MecPolicyIpVo mecPolicyIpVo: mecPolicyIps) {
            if(mecPolicyIpVo.isAllow(requestIp)) {
                isAllow = true;
                break;
            }
        }

        if(!isAllow) {
            throw new ShowUserMessageException("접근이 제한되었습니다.");
        }
    }

    private void loggingAccessLog(MecAccntVo accnt, String requestIp) {
        log.info("Access Request ID = {}", accnt.getAdmId());
        /*accessLogs */
        MecAccessLogVo mecAccessLogVo = new MecAccessLogVo();
        mecAccessLogVo.setAdmCode(accnt.getAdmCode());
        mecAccessLogVo.setIp(requestIp);

        accessLogService.createAccessLog(mecAccessLogVo);
    }

}
