package com.haili.project.projectfirst.enums;

/**
 * 功能描述: 本地用户的类型
 *
 * @Author: liuhaili
 * @Date: 2020-06-3, 周三, 19:35
 */
public enum ManageTypeEnum {

    TOURIST(0, "游客"),
    STUDENT(1,"学生"),
    TEACHER(2, "老师"),
    MANAGER(3, "管理员")

    ;


    private Integer code;

    private String message;

    /**
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * the code to set
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * the message to set
     * @param message 代表类型
     */
    public void setMessage(String message) {
        this.message = message;
    }

    ManageTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ManageTypeEnum getEnums(Integer code) {
        for (ManageTypeEnum manageTypeEnum : ManageTypeEnum.values()) {
            if (manageTypeEnum.getCode().equals(code)) {
                return manageTypeEnum;
            }
        }
        return null;
    }
}
