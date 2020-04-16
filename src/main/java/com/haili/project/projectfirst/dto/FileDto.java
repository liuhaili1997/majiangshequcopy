package com.haili.project.projectfirst.dto;

import lombok.Data;

/**
 *
 * @author Created by hailitortoise on 2020-04-15
 */
@Data
public class FileDto {

    /**
     * 成功的code
     */
    private Integer success;

    /**
     * 信息
     */
    private String message;

    /**
     * 地址
     */
    private String url;
}
