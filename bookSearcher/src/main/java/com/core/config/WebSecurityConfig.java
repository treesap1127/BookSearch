package com.core.config;

import net.mayeye.core.contants.MecWhilteList;
import net.mayeye.core.security.MecSecurityAuthenticationFailureHandler;
import net.mayeye.core.security.MecSecurityAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.mayeye.core.security.MecSecurityAuthenticationProvider;

import java.util.List;

/**
 * WebSecurityConfig.java
 * 관리자 Security Config
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-04-10         PJY      최초작성
 * @history 2019-04-19         PJY      권한 체크 필터 설정 추가
 * ==============================================
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MecSecurityAuthenticationProvider mecSecurityAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/font/**", "/errors/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> white = MecWhilteList.WHITE_ACCNT_LIST_UPPER;
        String[] whiteB = white.toArray(new String[white.size()]);
        http.authorizeRequests()
                    // 최상위 관리자만 접근 가능한 메뉴 ----------------- s
                    .antMatchers("/mec/accnt/accessLogs","/mec/accnt/accessLogs**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/accnt","/mec/accnt**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/policies","/mec/policies**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/roles/histories","/mec/roles/histories**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/roles","/mec/roles**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/siteCodes","/mec/siteCodes**").hasAnyAuthority("MAYEYE")
                    .antMatchers("/mec/categories","/mec/categories**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/program","/mec/program**").hasAnyAuthority("MAYEYE")
                    .antMatchers("/mec/satisfaction","/mec/satisfaction**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/fileManage","/mec/fileManage**").hasAnyAuthority("MAYEYE")
                    .antMatchers("/mec/userMenus/charge","/mec/userMenus/charge**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/userMenus","/mec/userMenus**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/menuStatistics","/mec/menuStatistics**").hasAnyAuthority(whiteB)
                    .antMatchers("/mec/article/codes","/mec/article/codes**").hasAnyAuthority("MAYEYE")
                    // 최상위 관리자만 접근 가능한 메뉴 ----------------- e
                    .antMatchers("/mec/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/mec/login")
                    .usernameParameter("admId")
                    .passwordParameter("pwd")
                    .permitAll()
                    .defaultSuccessUrl("/mec")
                    .successHandler(mecSecurityAuthenticationSuccessHandler())
                    .failureHandler(mecSecurityAuthenticationFailureHandler())
                    .and()
                .logout()
                    .logoutUrl("/mec/logout");
        /* TODO 최대 접속자 10 */
        http.sessionManagement()
                .maximumSessions(10)
                .expiredUrl("/mec/login");
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MecSecurityAuthenticationFailureHandler mecSecurityAuthenticationFailureHandler () {
        return new MecSecurityAuthenticationFailureHandler();
    }

    @Bean
    public MecSecurityAuthenticationSuccessHandler mecSecurityAuthenticationSuccessHandler () {
        return new MecSecurityAuthenticationSuccessHandler();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(mecSecurityAuthenticationProvider);
//    }
}
