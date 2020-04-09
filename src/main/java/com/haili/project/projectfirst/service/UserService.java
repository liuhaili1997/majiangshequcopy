package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.model.User;

/**
 * 对表user进行各种操作
 * @author Created by hailitortoise on 2020-04-07
 */
public interface UserService {

    /**
     * 判断记录是否存在，取决于account_id是否存在 更新还是创建新的记录
     * @param user 对象
     */
    void createOrUpdateUser(User user);
}
