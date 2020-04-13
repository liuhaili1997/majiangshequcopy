package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 * 接受前端传过来的jason格式
 * @author Created by hailitortoise on 2020-04-09
 */
@Data
public class CommentWebCreatorDto {

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 回复
     */
    private String content;

    /**
     * 类型：0：问题  1：回复
     */
    private Integer type;

}
