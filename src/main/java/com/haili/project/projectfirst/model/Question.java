package com.haili.project.projectfirst.model;

import lombok.Data;

/**
 * 发布问题的实体类
 * @author Created by hailitortoise on 2020-03-25
 */
@Data
public class Question {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;

    /**
     * 发布者
     */
    private String creator;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 标签
     */
    private String tag;
}
