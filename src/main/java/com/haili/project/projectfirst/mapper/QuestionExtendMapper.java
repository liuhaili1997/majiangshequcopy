package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.model.Question;

/**
 * 有些方法可以单独写在这里防止运行mvn命令丢失
 * @author Created by hailitortoise on 2020-04-09
 */
public interface QuestionExtendMapper {

    /**
     * 增加浏览数量 防止多线程问题
     * @param question 实体
     * @return 更新状态吗
     */
    int incViewCount(Question question);

    /**
     * 增加回复数量 防止多线程问题
     * @param question 实体
     * @return 更新状态吗
     */
    int incCommentCount(Question question);
}
