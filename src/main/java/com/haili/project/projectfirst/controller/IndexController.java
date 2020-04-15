package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.Question;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.QuestionService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
                        HttpServletRequest request, Model model) {
        //获取session内部的user，判断是否存在，才有权限
        User user = (User) request.getSession().getAttribute("user");

        PageInformationDto questionList = questionService.list(user.getAccountId(), currentPage, pageSize);
            model.addAttribute("questionList", questionList);
        return "index";
    }
}

