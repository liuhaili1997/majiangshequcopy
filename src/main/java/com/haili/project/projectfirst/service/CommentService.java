package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.model.Comment;

import java.util.List;

/**
 * 评论或回复一些功能的实现
 * @author Created by hailitortoise on 2020-04-09
 */
public interface CommentService {

    void insert(Comment comment);

    List<CommentDto> listByQuestionId(Long id);
}
