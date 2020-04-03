package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.enums.PersonEnum;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 个人中心界面
 * @author Created by hailitortoise on 2020-03-31
 */
@Controller
public class PersonSpaceController {

    @Autowired
    private QuestionService questionService;

    ///{action}:动态的切换路径来做这个内容和样式的替换

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer currentPage,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          HttpServletRequest request) {

        //获取session内部的user，判断是否存在，才有权限
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            return "redirect:/";
        }

        if ("questionList".equals(action)) {
            model.addAttribute("section", "questionList");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        } else if ("myInterest".equals(action)) {
            model.addAttribute("section", "myInterest");
            model.addAttribute("sectionName", "我的关注");
        } else if ("aboutMe".equals(action)) {
            model.addAttribute("section", "aboutMe");
            model.addAttribute("sectionName", "志同道合");
        } else if ("privateInfo".equals(action)) {
            model.addAttribute("section", "privateInfo");
            model.addAttribute("sectionName", "私人信息");
        }
        PageInformationDto pageInformationDto = questionService.listById(user.getAccountId(), currentPage, pageSize);
        model.addAttribute("questionList", pageInformationDto);
        return "personspace";

    }
}
