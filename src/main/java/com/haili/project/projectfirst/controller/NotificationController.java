package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.NotificationDto;
import com.haili.project.projectfirst.enums.NotificationEnums;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * 对回复的评论做出已读或者进行操作
 * @author Created by hailitortoise on 2020-04-15
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/replies/{id}")
    public String notification(@PathVariable(name = "id") Long id, Model model,
                               HttpServletRequest request) {
        //获取session内部的user，判断是否存在，才有权限
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDto notificationDto = notificationService.readReply(id, user);
        if (NotificationEnums.REPLY_COMMENT.getStatus().equals(notificationDto.getType())
                || NotificationEnums.REPLY_QUESTION.getStatus().equals(notificationDto.getType())) {
            return "redirect:/question/"+notificationDto.getOuterId();
        } else {
            return "redirect:/";
        }

    }
}
