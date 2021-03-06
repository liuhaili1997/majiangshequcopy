package com.haili.project.projectfirst.mapper;

import com.haili.project.projectfirst.dto.QuestionQueryDto;
import com.haili.project.projectfirst.model.Question;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过标签的正则表达式获得相应的问题
     * @param question 实体类
     * @return 结果集
     */
    List<Question> selectRelated(Question question);

    /**
     * 重构查询数据的方法合并
     * @param questionQueryDto 聚合类
     * @return 获得总的数量
     */
    Integer countByExample(QuestionQueryDto questionQueryDto);

    /**
     * 重构查询数据的方法，分页查询
     * @param questionQueryDto 聚合类
     * @return 获得总的数量
     */
    List<Question> selectBySearch(QuestionQueryDto questionQueryDto);
}
