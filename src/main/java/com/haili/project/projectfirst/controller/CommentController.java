package com.haili.project.projectfirst.controller;

import com.haili.project.projectfirst.dto.CommentDto;
import com.haili.project.projectfirst.dto.CommentWebCreatorDto;
import com.haili.project.projectfirst.dto.ResultDto;
import com.haili.project.projectfirst.enums.CommentTypeEnum;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.model.Comment;
import com.haili.project.projectfirst.model.User;
import com.haili.project.projectfirst.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论回复的实现和接受
 * @author Created by hailitortoise on 2020-04-09
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * @param commentWebCreatorDto 实体类
     * @return 结果
     * @RequestParam(name = "parentId") Long parentId 可以接受jason类型的变量
     * @RequestBody CommentDto commentDto 实体类接受jason类型的信息
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentWebCreatorDto commentWebCreatorDto,
                       HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDto.errorOf(CustomizeErrorEnums.NOT_LOGIN);
        }
        String content = commentWebCreatorDto.getContent();
        if (commentWebCreatorDto == null || StringUtils.isBlank(content)) {
            return ResultDto.errorOf(CustomizeErrorEnums.CONTENT_IS_EMPTY);
        }


        Comment comment = new Comment();
        Long parentId = commentWebCreatorDto.getParentId();
        if (null != parentId) {
            comment.setParentId(parentId);
        }
        if (StringUtils.isNotBlank(content)) {
            comment.setContent(content);
        }
        Integer type = commentWebCreatorDto.getType();
        if (null != type) {
            comment.setType(type);
        }
        Long currentTime = System.currentTimeMillis();
        comment.setGmtCreate(currentTime);
        comment.setGmtModified(currentTime);
        comment.setCommentator(user.getAccountId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0L);
        commentService.insert(comment, user);
        return ResultDto.success();
    }

    /**
     * 获取的是二级目录的信息和回复
     * @param id id
     * @return 结果数集
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDto commentSubList(@PathVariable(name = "id") Long id) {
        List<CommentDto> commentDtoList = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDto.success(commentDtoList);
    }
}
