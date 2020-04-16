package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.dto.QuestionDto;
import com.haili.project.projectfirst.model.Question;

import java.util.List;

/**
 * 当一个请求需要组装多个类的属性的时候就需要一个中间层来实现这些功能
 * @author Created by hailitortoise on 2020-03-26
 */
public interface QuestionService {

    /**
     * 获取整张的数据库的表来展示在首页上
     *
     * @param accountId   账户id
     * @param currentPage 当前页
     * @param pageSize    每页尺寸
     * @param search
     * @return 数据集合
     */
    PageInformationDto list(String accountId, Integer currentPage, Integer pageSize, String search);

    /**
     * 根据当前用户获取用户的提问
     *
     * @param id          id
     * @param currentPage 当前页
     * @param pageSize    页的尺寸
     * @return 返回分页查询数据
     */
    PageInformationDto listById(String id, Integer currentPage, Integer pageSize);

    /**
     * 通过id 获取整个question的信息
     * @param id id
     * @return 对象的信息
     */
    QuestionDto getById(Long id);

    /**
     * 判断记录是否存在，取决于id是否存在 更新还是创建新的记录
     * @param question 对象
     */
    void createOrUpdateQuestion(Question question);

    /**
     * 累加阅读数量
     * @param id 问题id
     */
    void incViewCount(Long id);

    /**
     * 根据tag查询对应的问题，并且显示出来
     * @param questionDto 接受参数的实体类
     * @return 结果集合
     */
    List<QuestionDto> selectRelated(QuestionDto questionDto);
}
