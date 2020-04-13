package com.haili.project.projectfirst.enums;
/**
 * 评论的类型
 * @author Created by hailitortoise on 2020-04-09
 */
public enum CommentTypeEnum {
    QUESTION(0, "问题"),
    COMMENT(1,"评论");


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

    CommentTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommentTypeEnum getEnums(Integer code) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getCode().equals(code)) {
                return commentTypeEnum;
            }
        }
        return null;
    }


    public static boolean isExit(Integer code) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
