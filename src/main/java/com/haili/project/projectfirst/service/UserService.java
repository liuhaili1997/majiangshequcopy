package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.UserOrManagerDto;
import com.haili.project.projectfirst.model.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过账户id获取用户信息
     *
     * @param accountId 账户ID
     * @return 流处理之后的map集合
     */
    List<UserOrManagerDto> getUserInfo(List<String> accountId);
}
