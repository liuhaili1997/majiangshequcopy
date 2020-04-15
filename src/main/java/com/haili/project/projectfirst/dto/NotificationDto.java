package com.haili.project.projectfirst.dto;

import com.haili.project.projectfirst.model.User;
import lombok.Data;

/**
 * model中的字段是对应数据库表中的字段不可以随意的修改，我们需要有一个中间层增加新的字段，
 * 促使可以满足前端对参数的要求
 * @author Created by hailitortoise on 2020-04-15
 */
@Data
public class NotificationDto {


    /**
     * id
     */
    private Long id;

    /**
     * 发表评论的人，对应user中的accountID
     */
    private String notifier;

    /**
     * 接受评论的人，对应user中的accountID
     */
    private String receiver;

    /**
     * 外部的id 可以是question的id 也可以是comment的ID
     */
    private Long outerId;

    /**
     * 类型：0：question  1：comment
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 状态 0:未读  1：已读
     */
    private Integer status;

    /**
     * 用户   notifierName直接获取姓名可以不需要对应的对象进行处理了
     */
    private User user;

    /**
     * 评论人的名字
     */
    private String notifierName;

    /**
     * 外部的标题名称 question的title或者是comment的content
     */
    private String outerTitle;

    /**
     * 是回复的问题还是回复的评论
     */
    private String notifyType;
}
