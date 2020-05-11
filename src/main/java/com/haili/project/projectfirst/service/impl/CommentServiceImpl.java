package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.enums.CommentTypeEnum;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.enums.NotificationEnums;
import com.haili.project.projectfirst.enums.NotificationStatusEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.mapper.*;
import com.haili.project.projectfirst.model.*;
import com.haili.project.projectfirst.service.CommentService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private CommentExtendMapper commentExtendMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    @Override
    public void insert(Comment comment, User commentator) {
        Long parentId = comment.getParentId();
        if (parentId == null || parentId == 0) {
            throw new CustomizeException(CustomizeErrorEnums.TARGET_PARAM_NOT_FOUND);
        }
        Integer type = comment.getType();
        if (type == null || !CommentTypeEnum.isExit(type)) {
            throw new CustomizeException(CustomizeErrorEnums.TYPE_PARAM_WRONG);
        }
        comment.setCommentCount(0L);
        if (CommentTypeEnum.COMMENT.getCode().equals(type)) {
            //回复的时评论
            Comment commentById = commentMapper.selectByPrimaryKey(parentId);
            Question question = questionMapper.selectByPrimaryKey(commentById.getParentId());
            if (null == commentById) {
                throw new CustomizeException(CustomizeErrorEnums.COMMENT_NOT_FOUND);
            }
            if (null == question) {
                throw new CustomizeException(CustomizeErrorEnums.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            /*增加评论的评论数量*/
            Comment commentInc = new Comment();
            commentInc.setId(parentId);
            commentInc.setCommentCount(1L);
            commentExtendMapper.incCommentCount(commentInc);
            //创建通知
            //todo commentById.getContent() 存在歧义，别人评论了评论是应该获得评论内容，还是我评论的问题的title，就某问题品论了评论
            createNotify(comment, commentById.getCommentator(), commentator.getName(), commentById.getContent(), NotificationEnums.REPLY_COMMENT,question.getId());
        } else {
            //回复问题 可以用于todo
            Question question = questionMapper.selectByPrimaryKey(parentId);
            if (null == question) {
                throw new CustomizeException(CustomizeErrorEnums.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Question questionInc = new Question();
            questionInc.setId(parentId);
            questionInc.setCommentCount(1);
            questionExtendMapper.incCommentCount(questionInc);
            createNotify(comment,question.getCreator(),commentator.getName(), question.getTitle(), NotificationEnums.REPLY_QUESTION,question.getId());
        }
    }

    /**
     * 生成通知表单
     *
     * @param comment           评论对象
     * @param receiver          通过parentId获得的被评论的ID
     * @param creator           姓名
     * @param outerTitle        外部标题
     * @param notificationEnums type类型
     */
    private void createNotify(Comment comment, String receiver, String creator, String outerTitle, NotificationEnums notificationEnums, Long outertId) {
        String commentator = comment.getCommentator();
        //将下面的if (StringUtils.isNotBlank(commentator) && commentator.equals(receiver)) { 就自己写的评论不会通知自己
        /*if (StringUtils.isNotBlank(commentator) && commentator.equals(receiver)) {
            return;
        }*/
        //通知表的生成
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        //通知是问题还是评论
        notification.setType(notificationEnums.getStatus());
        notification.setOuterId(outertId);
        //评论人
        if (StringUtils.isNotBlank(commentator)) {
            notification.setNotifier(commentator);
        }
        notification.setStatus(NotificationStatusEnums.UNREAD.getStatus());
        //评论的接受者
        notification.setReceiver(receiver);
        notification.setNotifierName(creator);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    @Override
    public List<CommentDto> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        //要获取的只是需要问题对应的评论就好了，而不是id对应的评论
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getCode());
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
