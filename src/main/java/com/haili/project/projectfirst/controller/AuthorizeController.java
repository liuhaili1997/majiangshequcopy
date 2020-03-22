package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.AccessTokenDto;
import com.haili.project.projectfirst.dto.GithubUser;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.provider.GitHubProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
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
        if (null != githubUser) {
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //如果cookie不为空，登录成功，写cookie和session   redirect:渲染页面去除地址
            request.getSession().setAttribute("githubUser", githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
