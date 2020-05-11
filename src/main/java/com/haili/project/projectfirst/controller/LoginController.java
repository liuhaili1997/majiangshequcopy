package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.mapper.ManagerMapper;
import com.haili.project.projectfirst.model.Manager;
import com.haili.project.projectfirst.model.ManagerExample;
import com.haili.project.projectfirst.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 注册和登录的实际操作
 *
 * @author Created by hailitortoise on 2020-05-08
 */
@Controller
public class LoginController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerMapper managerMapper;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerManager(@RequestParam(value = "userName", required = false) String name,
                                  @RequestParam(value = "userPass", required = false) String password,
                                  @RequestParam(value = "file", required = false) MultipartFile file,
                                  @RequestParam(value = "userEmail", required = false) String email,
                                  Model model) {
        /*回显*/
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("file", file);

        if (StringUtils.isBlank(name)) {
            model.addAttribute("registerError", "姓名不能为空！！");
            return "register";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("registerError", "密码不为空！！");
            return "register";
        }
        if (StringUtils.isBlank(email)) {
            model.addAttribute("registerError", "请填写邮箱地址！！");
            return "register";
        }
        if (null == file) {
            model.addAttribute("registerError", "记得上传头像哦！！");
            return "register";
        }
        Manager manager = new Manager();
        manager.setName(name);
        manager.setPassword(password);
        manager.setEmail(email);
        managerService.createNewRecord(manager, file);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginManager(@RequestParam(value = "managerName", required = false) String name,
                               @RequestParam(value = "mima", required = false) String password,
                               Model model, HttpServletResponse response) {
        /*回显*/
        model.addAttribute("name", name);
        if (StringUtils.isBlank(name)) {
            model.addAttribute("loginError", "姓名不能为空！！");
            return "login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("loginError", "密码不为空！！");
            return "login";
        }
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria()
                .andNameEqualTo(name)
                .andPasswordEqualTo(password);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if (CollectionUtils.isEmpty(managers)) {
            model.addAttribute("loginError", "账号和密码不对，请重新填写");
            return "login";
        }
        /*这里就需要修改user和token了 设置SessionInterceptor中的token的查询，获得用户的信息，但是两个实体类之间是有区别的，要整体的细致观察*/
        response.addCookie(new Cookie("token", managers.get(0).getToken()));
        return "redirect:/";
    }
}
