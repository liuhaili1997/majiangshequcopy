package com.haili.project.projectfirst.dto;

import com.haili.project.projectfirst.model.User;
import lombok.Data;

/**
 * question 实体类中是对应数据库中的字段，不可以随意的增加 就需要中间层接受或传递的实体类questionDto
 * @author Created by hailitortoise on 2020-03-26
 */
@Data
public class QuestionDto {

    /**
     * id
     */
    private Long id;

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

    /**
     * 用户头像图片路径
     */
    private String avatarUrl;

    /**
     * 用户对象
     */
    private User user;
}
