package com.core.module.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mayeye.core.commons.ShowUserMessageException;
import net.mayeye.core.module.accessLog.MecAccessLogVo;
import net.mayeye.core.module.accessLog.service.MecAccessLogService;
import net.mayeye.core.module.accnt.MecAccntVo;
import net.mayeye.core.module.articleRole.MecArticleRoleVo;
import net.mayeye.core.module.articleRole.service.MecArticleRoleService;
import net.mayeye.core.module.contentsRole.MecContentsRoleVo;
import net.mayeye.core.module.contentsRole.service.MecContentsRoleService;
import net.mayeye.core.module.policyIp.MecPolicyIpDto;
import net.mayeye.core.module.policyIp.MecPolicyIpVo;
import net.mayeye.core.module.policyIp.service.MecPolicyIpService;
import net.mayeye.core.module.roleMenu.service.MecRoleMenuService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MecSecurityAuthenticationProvider.java
 * MecSecurityAuthenticationProvider
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-04-10         PJY      최초작성
 * @history 2019-04-23         PJY      로그인 프로세스 로깅 처리, accessLogging 처리
 * ==============================================
 */
@Deprecated
@RequiredArgsConstructor
@Slf4j
public class MecSecurityAuthenticationProvider implements AuthenticationProvider {

    private final MecSecurityUserDetailsService userDetailsService;
    private final MecPolicyIpService policyIpService;
    private final MecRoleMenuService roleMenuService;
    private final PasswordEncoder passwordEncoder;
    private final MecAccessLogService accessLogService;
    private final MecArticleRoleService articleRoleService;
    private final MecContentsRoleService contentsRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Login Process is Started...");
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        authToken.getName();
        MecSecurityUserDetailsAdapter userDetailsAdapter = (MecSecurityUserDetailsAdapter) userDetailsService.loadUserByUsername(authToken.getName());
        MecAccntVo userDetails = userDetailsAdapter.getMecAccntVo();

        if(userDetails == null) {
            throw new ShowUserMessageException(authToken.getName());
        }
        if(!passwordEncoder.matches(authToken.getCredentials().toString(),userDetails.getPwd())) {
            throw new ShowUserMessageException("비밀번호가 일치하지 않습니다.");
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestIp = request.getRemoteAddr();

        log.info("Access Request ID = {}", userDetails.getAdmId());
        /*accessLogs */
        MecAccessLogVo mecAccessLogVo = new MecAccessLogVo();
        mecAccessLogVo.setAdmCode(userDetails.getAdmCode());
        mecAccessLogVo.setIp(requestIp);

        accessLogService.createAccessLog(mecAccessLogVo);
        /**/

        /*check policy Ips*/
        log.info("Check PolicyIp...");
        MecPolicyIpDto.Login policyIpDto = new MecPolicyIpDto.Login(userDetails.getAdmCode());
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
        /**/

        /*Roles */
        log.info("Check Granted Roles...");
        userDetails.setAllowMenus(roleMenuService.findDistinctRoleMenusByAccntId(userDetails.getAdmCode()));

        /* */

        /* ArticleRoles */
        log.info("Check Granted Articles...");
        List<MecArticleRoleVo> allowArticles = articleRoleService.findArticleRoles(userDetails.getAdmCode());
        userDetails.setAllowArticles(allowArticles);
        /* */

        /* ContentsRoles */
        log.info("Check Granted Contents...");
        List<MecContentsRoleVo> allowContents = contentsRoleService.findContentsRoles(userDetails.getAdmCode());
        userDetails.setAllowContentses(allowContents);
        /* */

        log.info("Login Process is Complete...");
        return new UsernamePasswordAuthenticationToken(userDetails,authToken.getCredentials());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
