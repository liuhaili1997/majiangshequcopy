package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.enums.CommentTypeEnum;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.mapper.CommentMapper;
import com.haili.project.projectfirst.mapper.QuestionExtendMapper;
import com.haili.project.projectfirst.mapper.QuestionMapper;
import com.haili.project.projectfirst.mapper.UserMapper;
import com.haili.project.projectfirst.model.*;
import com.haili.project.projectfirst.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实现评论或者回复具体操作
 * @author Created by hailitortoise on 2020-04-09
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(Comment comment) {
        Long parentId = comment.getParentId();
        if (parentId == null || parentId == 0) {
            throw new CustomizeException(CustomizeErrorEnums.TARGET_PARAM_NOT_FOUND);
        }
        Integer type = comment.getType();
        if (type == null || !CommentTypeEnum.isExit(type)) {
            throw new CustomizeException(CustomizeErrorEnums.TYPE_PARAM_WRONG);
        }

        if (CommentTypeEnum.COMMENT.getCode().equals(type)) {
            //回复的时评论
            Comment commentById = commentMapper.selectByPrimaryKey(parentId);
            if (null == commentById) {
                throw new CustomizeException(CustomizeErrorEnums.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(parentId);
            if (null == question) {
                throw new CustomizeException(CustomizeErrorEnums.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Question questionInc = new Question();
            questionInc.setId(parentId);
            questionInc.setCommentCount(1);
            questionExtendMapper.incCommentCount(questionInc);
        }
    }

    @Override
    public List<CommentDto> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        //要获取的只是需要问题对应的评论就好了，而不是id对应的评论
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getCode());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (CollectionUtils.isEmpty(comments)) {
            return new ArrayList<>();
        }
        //获取评论人的accountId，并查询对应的user 转换为Map 简化for循环 n*n ->n*1
        List<String> accountIds = comments.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdIn(accountIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getAccountId, Function.identity(), (oldValue, newValue) -> newValue));
        //使用流操作对集合进行字段的整合
        List<CommentDto> commentDtoList = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            String commentator = comment.getCommentator();
            commentDto.setUser(userMap.get(commentator));
            return commentDto;
        }).collect(Collectors.toList());
        return commentDtoList;
    }
}
