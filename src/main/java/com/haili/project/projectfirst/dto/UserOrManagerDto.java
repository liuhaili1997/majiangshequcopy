package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 * 功能描述: 集中获得用户数据的综合
 *
 * @Author: liuhaili
 * @Date: 2020-05-28, 周四, 13:32
 */
@Data
public class UserOrManagerDto {


    /**
     * id
     */
    private Long id;

    /**
     * 账户id user.id 8位数字  manager.id 9位数字
     */
    private String accountId;

    /**
     * 姓名
     */
    private String name;

    /**
     * token 符记
     */
    private String token;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户类型
     */
    private Byte type;

    /**
     * 描述
     */
    private String bio;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;
}
