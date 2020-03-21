package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 * 当参数达到两个以上的时候最好是写一个类，封装成对象操作，来进行数据的接收
 * dto： 数据传输的模型
 *
 * @author lhl
 * @date 2020-03-20
 */
@Data
public class AccessTokenDto {

    /**
     * id
     */
    private String clientId;

    /**
     * OAthor apps的密码
     */
    private String clientSecret;

    /**
     * code
     */
    private String code;

    /**
     * url
     */
    private String redirectUri;

    /**
     * 状态码
     */
    private String state;
}
