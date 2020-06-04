package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.GitHubUserDto;
import com.haili.project.projectfirst.dto.ResultDto;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 功能描述: 用户信息展示
 *
 * @Author: liuhaili
 * @Date: 2020-05-31, 周日, 15:2
 */
@Controller
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @GetMapping("/management")
    public String displayManagement(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            throw new CustomizeException(CustomizeErrorEnums.NOT_LOGIN);
        }
        //文章总数
        Long sumOfQuestion = managementService.getSumOfQuestion();
        //当日的发布文章数
        Long questionsOfToday = managementService.getQuestionsOfToday();
        //获取当前用户数
        Long sumOfUser = managementService.getSumOfUser();
        //github 授权的用户表
        List<GitHubUserDto> listOfUser = managementService.getListOfUser();
        //本地用户管理系统
        Map<Integer, List<GitHubUserDto>> listOfManage = managementService.getListOfManage();

        model.addAttribute("sumOfQuestion", sumOfQuestion);
        model.addAttribute("questionsOfToday", questionsOfToday);
        model.addAttribute("sumOfUser", sumOfUser);
        model.addAttribute("table1", listOfUser);
        model.addAttribute("table2", listOfManage.get(2));
        model.addAttribute("table3", listOfManage.get(1));
        return "management";
    }

    @GetMapping("/management/delete/{id}")
    public ResultDto deleteUser(@PathVariable(name = "id") Long id) {
        Integer integer = managementService.deleteUser(id);
        if (integer == 0) {
            return ResultDto.errorOf(400, "删除出错，无法正常删除");
        }
        return ResultDto.success();
    }
}
