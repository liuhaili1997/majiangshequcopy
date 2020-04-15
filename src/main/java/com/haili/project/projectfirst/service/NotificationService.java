package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.NotificationDto;
import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.model.User;

/**
 * 通知表单的相关实现查询操作
 * @author Created by hailitortoise on 2020-04-15
 */
public interface NotificationService {

    /**
     * 接口隔离
     *
     * @param accountId   账户id
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return 查询和整理的数据
     */
    PageInformationDto list(String accountId, Integer currentPage, Integer pageSize);

    /**
     * 查询当前用户还有多少未读的评论数
     * @param accountId 账户id
     * @return 结果
     */
    Long unReadCount(String accountId);

    /**
     * 进行操作对自己的评论已读
     *
     * @param id   id
     * @param user 用户对象
     * @return 聚合数据
     */
    NotificationDto readReply(Long id, User user);
}
