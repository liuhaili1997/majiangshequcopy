package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现类
 * @author Created by hailitortoise on 2020-04-07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createOrUpdateUser(User user) {
        String accountId = user.getAccountId();
        User dbUser = userMapper.findByAccountId(accountId);
        if (dbUser == null) {
            //插入数据
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新操作数据的时间和token
            dbUser.setGmtModified(System.currentTimeMillis());
            String avatarUrl = user.getAvatar();
            if (StringUtils.isNotBlank(avatarUrl)) {
                dbUser.setAvatar(avatarUrl);
            }
            String name = user.getName();
            if (StringUtils.isNotBlank(name)) {
                dbUser.setName(name);
            }
            dbUser.setToken(user.getToken());
            userMapper.updateUser(dbUser);
        }
    }
}
