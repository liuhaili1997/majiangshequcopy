package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.AccessTokenDto;
import com.haili.project.projectfirst.dto.GithubUser;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.provider.GitHubProvider;
import com.haili.project.projectfirst.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 这个controller不仅仅是接收url解析cod，还包括调用access_token,访问github，判断是否正确，返回真正的access_token
 * 通过这个调用user-api获取用户信息
 *
 * @author lhl
 * @date 2020-03-20
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClientId(clientId);
        accessTokenDto.setClientSecret(clientSecret);
        /**isNotBlank与isEmpty之间的区别：判断空格的字符串*/
        if (StringUtils.isNotBlank(code)) {
            accessTokenDto.setCode(code);
        }
        accessTokenDto.setRedirectUri(redirectUri);
        if (StringUtils.isNotBlank(state)) {
            accessTokenDto.setState(state);
        }
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if (null != githubUser && null != githubUser.getId()) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            String name = githubUser.getName();
            if (StringUtils.isNotBlank(name)) {
                user.setName(name);
            } else {
                user.setName("林深时见鹿");
            }
            user.setAccountId(String.valueOf(githubUser.getId()));
            //当头像地址大于128个varchar时，是不可以将数据存入数据库的
            user.setAvatar(githubUser.getAvatarUrl());
            userService.createOrUpdateUser(user);
            response.addCookie(new Cookie("token", token));
            //如果cookie不为空，登录成功，写cookie和session   redirect:渲染页面去除地址
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    /**
     * 退出登录，就必须要清除token cookie session
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request,
                         HttpServletResponse response) {
        //清除session
        request.getSession().removeAttribute("user");
        //清除已知的cookie 建立一个重名的cookie，key对应的value赋值为null 设置MaxAge为0
        Cookie cookie = new Cookie("token", null);
        //立即删除型
        cookie.setMaxAge(0);
        //项目的所有目录均有效
        cookie.setPath("/");
        //重新写入将覆盖之前的cookie
        response.addCookie(cookie);

        return "redirect:/";
    }
}
