package com.haili.project.projectfirst.interceptor;

import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.model.UserExample;
import com.haili.project.projectfirst.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 拦截器实现
 * @author Created by hailitortoise on 2020-04-02
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                UserExample userExample = new UserExample();
                userExample.createCriteria()
                        .andTokenEqualTo(token);
                List<User> userList = userMapper.selectByExample(userExample);
                if (!CollectionUtils.isEmpty(userList)) {
                    request.getSession().setAttribute("user", userList.get(0));
                    Long unReadCount = notificationService.unReadCount(userList.get(0).getAccountId());
                    request.getSession().setAttribute("unReadNotificationCount", unReadCount);
                }
                break;
            }
        }
        //对所有请求做拦截，将user放到session内部  true 不中断程序
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
