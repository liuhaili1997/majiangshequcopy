package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 * 重构方法
 * @author Created by hailitortoise on 2020-04-16
 */
@Data
public class QuestionQueryDto {

    /**
     * 搜索内容
     */
    private String search;

    /**
     * 当前页
     */
    private Integer currantPage;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
