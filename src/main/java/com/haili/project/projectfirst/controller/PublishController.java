package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.Question;
import com.haili.project.projectfirst.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 发布的controller
 * @author Created by hailitortoise on 2020-03-25
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    /**如果是get请求：跳转页面 如果是post请求：处理数据*/
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            HttpServletRequest request, Model model) {
        //这里获取用于回显在前端
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (StringUtils.isBlank(title)) {
            model.addAttribute("erro", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("erro", "请您描述清楚您的问题，方便为您更快更准确的解决");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("erro", "请您最少写一个标签，方便查找");
            return "publish";
        }
        //获取session内部的user，判断是否存在，才有权限
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            model.addAttribute("erro", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        //个人觉得这里可以换成account_id
        question.setCreator(user.getAccountId());
        Long currentTime = System.currentTimeMillis();
        question.setGmtCreate(currentTime);
        question.setGmtModified(currentTime);
        questionMapper.create(question);
        return "redirect:/";
    }
}
