package com.haili.project.projectfirst.dto;

import com.haili.project.projectfirst.enums.CustomizeErrorEnums;
import com.haili.project.projectfirst.exception.CustomizeException;
import lombok.Data;

/**
 * 告诉前端是否成功
 * @author Created by hailitortoise on 2020-04-09
 */
@Data
public class ResultDto<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 泛型T：可以代表一切，既可以是变量也可以是集合或对象
     */
    private T data;

    public static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorEnums enums) {
        return errorOf(enums.getCode(), enums.getMessage());
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDto success() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static <T> ResultDto success(T t) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }

}
