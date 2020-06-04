package com.haili.project.projectfirst.dto;

import com.haili.project.projectfirst.model.User;
import lombok.Data;

/**
 * 用于接受不是前端的参数，而是后端查询过来的参数
 * @author Created by hailitortoise on 2020-04-13
 */
@Data
public class CommentDto {

    /**
     * id
     */
    private Long id;

    /**
     * 对应的是问题还是评论的id 父类
     */
    private Long parentId;

    /**
     * 类型：0：问题  1：评论
     */
    private Integer type;

    /**
     * 评论人的account_id
     */
    private String commentator;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;

    /**
     * 点赞人数
     */
    private Long likeCount;

    /**
     * 点赞人数
     */
    private Long commentCount;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户信息
     */
    private UserOrManagerDto user;

}
