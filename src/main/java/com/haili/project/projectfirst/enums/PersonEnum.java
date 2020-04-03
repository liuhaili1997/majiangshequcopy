package com.haili.project.projectfirst.enums;


/**
 * menu的枚举类型
 * @author Created by hailitortoise on 2020-04-01
 */
public enum PersonEnum {

    QUESTIONLIST(1,"questionlist"),
    REPLIES(2,"replies"),
    MYINTEREST(3, "myInterest"),
    ABOUTME(4, "aboutMe"),
    PRIVATEINFO(5,"privateInfo")
    ;

    /**
     * 整型代号
     */
    private Integer code;

    /**
     * 表示的数据
     */
    private String message;

    PersonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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

    public static PersonEnum getEnum(Integer code) {
        for (PersonEnum personEnum : PersonEnum.values()) {
            if (personEnum.getCode().equals(code)) {
                return personEnum;
            }
        }
        return null;
    }
}
