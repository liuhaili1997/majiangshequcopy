package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.enums.CommentTypeEnum;
import com.haili.project.projectfirst.service.CommentService;
import com.haili.project.projectfirst.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 用于点击我的问题中的问题，展示页面，并且评论的功能
 * @author Created by hailitortoise on 2020-04-03
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String questionDisplay(@PathVariable(name = "id") Long id, Model model) {

        QuestionDto questionDto = questionService.getById(id);

        //根据tag查询对应的问题，并且显示出来
        List<QuestionDto> relatedQuestions = questionService.selectRelated(questionDto);
        //展示评论人数和内容
        List<CommentDto> commentDtoList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incViewCount(id);
        model.addAttribute("question", questionDto);
        model.addAttribute("comments", commentDtoList);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
