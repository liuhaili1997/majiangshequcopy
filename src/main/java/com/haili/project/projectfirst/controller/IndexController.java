package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lhl
 * @date 2020-03-19
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page", defaultValue = "1") Integer currentPage,
                        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                        @RequestParam(name = "search", required = false) String search,
                        HttpServletRequest request, Model model) {
        //获取session内部的user，判断是否存在，才有权限
        User user = (User) request.getSession().getAttribute("user");

        PageInformationDto questionList = questionService.list(currentPage, pageSize, search);
        model.addAttribute("questionList", questionList);
        model.addAttribute("search", search);
        return "index";
    }
}

