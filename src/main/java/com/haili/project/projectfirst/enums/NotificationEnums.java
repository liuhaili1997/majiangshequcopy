package com.haili.project.projectfirst.enums;
/**
 * 回复问题的type
 * @author Created by hailitortoise on 2020-04-15
 */
public enum NotificationEnums {

    REPLY_QUESTION(0, "回复了问题"),
    REPLY_COMMENT(1,"回复了评论");


    /**
     * 状态
     */
    private Integer status;

    /**
     * 信息
     */
    private String message;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    NotificationEnums(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static String nameOfType(Integer type) {
        for (NotificationEnums notificationEnums : NotificationEnums.values()) {
            if (notificationEnums.getStatus().equals(type)) {
                return notificationEnums.message;
            }
        }
        return "";
    }
}
