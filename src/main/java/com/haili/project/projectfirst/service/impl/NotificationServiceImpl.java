package com.haili.project.projectfirst.service.impl;

import com.haili.project.projectfirst.dto.NotificationDto;
import com.haili.project.projectfirst.dto.PageInformationDto;
import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.enums.NotificationEnums;
import com.haili.project.projectfirst.enums.NotificationStatusEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import com.haili.project.projectfirst.mapper.NotificationMapper;
import com.haili.project.projectfirst.model.*;
import com.haili.project.projectfirst.service.NotificationService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现类
 * @author Created by hailitortoise on 2020-04-15
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public PageInformationDto list(String accountId, Integer currentPage, Integer pageSize) {
        //获取分页的真实数据
        PageInformationDto<NotificationDto> pageInformationDto = new PageInformationDto<>();
        //分页数据上传
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(accountId);
        Integer total = (int)notificationMapper.countByExample(notificationExample);
        //获取总页数
        int totalPage;
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        pageInformationDto.setTotalPage(totalPage);
        //健壮性
        if (currentPage < 1) {
            currentPage = 1;
        }
        //totalPage 总页数
        if (currentPage > totalPage) {
            currentPage =  totalPage;
        }
        pageInformationDto.setPageInformation(total, currentPage, pageSize);

        int offSize = currentPage < 1 ? 0 : pageSize * (currentPage - 1);
        NotificationExample notificationExample1 = new NotificationExample();
        notificationExample1.setOrderByClause("gmt_create desc");
        notificationExample1.createCriteria()
                .andReceiverEqualTo(accountId);
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(notificationExample1, new RowBounds(offSize, pageSize));
        if (CollectionUtils.isEmpty(notificationList)) {
            return new PageInformationDto<>();
        }
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        NotificationDto notificationDto;
        for (Notification notification : notificationList) {
            notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setNotifyType(NotificationEnums.nameOfType(notification.getType()));
            notificationDtoList.add(notificationDto);
        }
        pageInformationDto.setData(notificationDtoList);

        return pageInformationDto;
    }

    @Override
    public Long unReadCount(String accountId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(accountId)
                .andStatusEqualTo(NotificationStatusEnums.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    @Override
    public NotificationDto readReply(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorEnums.READ_NOTIFICATION_NOT_EXIT);
        }
        if (!notification.getReceiver().equals(user.getAccountId())) {
            throw new CustomizeException(CustomizeErrorEnums.READ_NOTIFICATION_FAIL);
        }
        //默认为未读，改为已读状态
        notification.setStatus(NotificationStatusEnums.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDto);
        notificationDto.setNotifyType(NotificationEnums.nameOfType(notification.getType()));
        return notificationDto;
    }
}
