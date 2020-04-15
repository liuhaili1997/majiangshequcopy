package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.cache.TagCache;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.model.Question;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布的controller
 *
 * @author Created by hailitortoise on 2020-03-25
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**
     * 如果是get请求：跳转页面 如果是post请求：处理数据
     */
    @GetMapping("/publish")
    public String publish(Model model) {

        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            HttpServletRequest request, Model model) {
        //这里获取用于回显在前端
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
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
        String inValid = TagCache.filterInValid(tag);
        if (StringUtils.isNotBlank(inValid)) {
            model.addAttribute("erro", "你自己写了的标签是不能存的哦！！非法标签："+inValid);
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
        question.setId(id);

        questionService.createOrUpdateQuestion(question);
        return "redirect:/";
    }

    /**
     * GetMapping中的id可以通过注解 @PathVariable(name = "id")获得
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        //todo 编辑不需要添加浏览数量
        QuestionDto question = questionService.getById(id);
        //这里获取用于回显在前端
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
}
