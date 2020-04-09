package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用于点击我的问题中的问题，展示页面，并且评论的功能
 * @author Created by hailitortoise on 2020-04-03
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String questionDisplay(@PathVariable(name = "id") Integer id,
                                  Model model) {
        QuestionDto questionDto = questionService.getById(id);
        //累加阅读数
        questionService.incViewCount(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
