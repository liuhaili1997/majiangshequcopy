package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.model.User;

/**
 * 对表user进行各种操作
 * @author Created by hailitortoise on 2020-04-07
 */
public interface UserService {
    void createOrUpdateUser(User user);
}
