package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 * 功能描述: 用于显示用户的信息
 *
 * @Author: liuhaili
 * @Date: 2020-06-3, 周三, 18:43
 */
@Data
public class GitHubUserDto {


    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 注册时间
     */
    private String registerTime;

    /**
     * 发布数量
     */
    private Long publishCount;

    /**
     * 评论数量
     */
    private Long commentCount;

    /**
     * 操作
     */
    private String operator;

}
