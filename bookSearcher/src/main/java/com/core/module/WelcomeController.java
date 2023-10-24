package com.core.module;

import java.util.List;
import java.util.Map;

import net.mayeye.core.contants.MecWhilteList;
import net.mayeye.core.module.accnt.MecAccntVo;
import net.mayeye.core.module.visitor.MecVisitorVo;
import net.mayeye.core.module.visitor.service.MecVisitorService;
import net.mayeye.core.security.AuthUser;
import net.mayeye.core.security.MecSecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.mayeye.core.module.accessLog.MecAccessLogVo;
import net.mayeye.core.module.accessLog.service.MecAccessLogService;
import net.mayeye.core.module.article.MecArticleVo;
import net.mayeye.core.module.article.service.MecArticleService;
import net.mayeye.core.module.contents.MecContentsVo;
import net.mayeye.core.module.contents.service.MecContentsService;

import javax.servlet.http.HttpServletRequest;

/**
 * WelcomeController.java
 * 대시보드 컨트롤러
 * ==============================================
 *
 * @author PJY
 * @history 작성일            작성자     변경내용
 * @history 2019-04-23         PJY      최초작성
 * ==============================================
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WelcomeController {

    private final MecAccessLogService accessLogService;
    private final MecArticleService articleService;
    private final MecContentsService contentsService;

    @GetMapping("mec")
    public String dashBoard(
            Model model,
            @AuthUser MecAccntVo mecAccntVo
    ) {
        if(!MecWhilteList.WHITE_ACCNT_LIST.contains(mecAccntVo.getAdmId())){
            return "redirect:/mec/article/master?mno=1";
        }
        MecAccessLogVo mecAccessLogVo = new MecAccessLogVo();
        List<MecAccessLogVo> accessLogs = accessLogService.findAccessLogs(mecAccessLogVo);

        MecArticleVo mecArticleVo = new MecArticleVo();
        List<MecArticleVo> articles = articleService.findRecentArticles(mecArticleVo);

        Map<String,Object> dashBoard = articleService.countDashBoard();

        MecContentsVo mecContentsVo = new MecContentsVo();
        List<MecContentsVo> contents = contentsService.findRecentContents(mecContentsVo);

        model.addAttribute("recentArticles",articles);
        model.addAttribute("accessLogs",accessLogs);
        model.addAttribute("dashBoard",dashBoard);
        model.addAttribute("recentContents", contents);
        return "index.core";
    }

    @RequestMapping("message")
    public String message(HttpServletRequest request, Model model) {
        model.addAttribute("message", request.getAttribute("message"));
        model.addAttribute("forward", request.getAttribute("forward"));

        return "core/message";
    }

}
