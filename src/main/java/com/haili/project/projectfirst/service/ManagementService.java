package com.haili.project.projectfirst.service;

import com.haili.project.projectfirst.dto.GitHubUserDto;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: 用于实现management的数据处理和展示
 *
 * @Author: liuhaili
 * @Date: 2020-06-2, 周二, 19:41
 */
public interface ManagementService {

    /**
     * 获取文章数量
     *
     * @return 总数
     */
    Long getSumOfQuestion();

    /**
     * 当天的发布文章
     *
     * @return 数量
     */
    Long getQuestionsOfToday();

    /**
     * 获取总的用户数
     *
     * @return 总数
     */
    Long getSumOfUser();

    /**
     * 获取用户信息
     *
     * @return 返回结果
     */
    List<GitHubUserDto> getListOfUser();

    /**
     * 查询本地用户表中的数据内容
     *
     * @return 返回结果
     */
    Map<Integer,List<GitHubUserDto>> getListOfManage();

    /**
     * 删除操作
     * @param id id
     * @return 操作是否成功
     */
    Integer deleteUser(Long id);
}
