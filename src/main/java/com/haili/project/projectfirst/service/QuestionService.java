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
     * @param currentPage 当前页
     * @param pageSize    每页尺寸
     * @return 数据集合
     */
    PageInformationDto list(Integer currentPage, Integer pageSize);

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
    QuestionDto getById(Integer id);
}
