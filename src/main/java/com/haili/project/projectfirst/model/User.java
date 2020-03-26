package com.haili.project.projectfirst.model;

import lombok.Data;

@Data
public class User {

    /**
     * id
     */
    private int id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;

    /**
     * 图片地址
     */
    private String avatar;
}
