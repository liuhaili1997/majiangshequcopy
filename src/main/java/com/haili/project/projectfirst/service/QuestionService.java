package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.PageInformationDto;

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
}
