package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.enums.CommentTypeEnum;
import com.haili.project.projectfirst.model.Comment;
import com.haili.project.projectfirst.model.User;

import java.util.List;

/**
 * 评论或回复一些功能的实现
 * @author Created by hailitortoise on 2020-04-09
 */
public interface CommentService {

    void insert(Comment comment, User commentator);

    List<CommentDto> listByTargetId(Long id, CommentTypeEnum type);
}
