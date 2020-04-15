package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.model.Comment;
import com.haili.project.projectfirst.model.Question;

/**
 * comment表的额外方法
 * @author Created by hailitortoise on 2020-04-14
 */
public interface CommentExtendMapper {

    /**
     * 增加回复数量 防止多线程问题
     * @param comment 实体
     * @return 更新状态吗
     */
    int incCommentCount(Comment comment);
}