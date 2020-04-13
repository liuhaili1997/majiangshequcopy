package com.haili.project.projectfirst.exception;

import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.enums.InterCustomizeErrorEnums;

/**
 * 捕获异常
 * @author Created by hailitortoise on 2020-04-08
 * 继承RuntimeException 是为了项目再调用的时候不报异常中断，只是再advice中捕获
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(InterCustomizeErrorEnums enums) {
        this.code = enums.getCode();
        this.message = enums.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
