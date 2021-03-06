package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 *
 * @author Created by hailitortoise on 2020-03-20
 */
@Data
public class GithubUser {

    /**
     * 用户名
     */
    private String name;

    /**
     * 唯一id
     */
    private Long id;

    /**
     * bio描述
     */
    private String bio;

    /**
     * 头像地址
     */
    private String avatarUrl;
}
