package com.haili.project.projectfirst.enums;
/**
 * 回复问题的type
 * @author Created by hailitortoise on 2020-04-15
 */
public enum NotificationStatusEnums {

    UNREAD(0), READ(1);


    /**
     * 状态 0:对通知未读 1：对通知已读
     */
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    NotificationStatusEnums(Integer status) {
        this.status = status;
    }
}
