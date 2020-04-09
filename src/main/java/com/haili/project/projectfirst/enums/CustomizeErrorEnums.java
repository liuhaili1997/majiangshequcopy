package com.haili.project.projectfirst.enums;
/**
 * 错误码对应的枚举
 * @author Created by hailitortoise on 2020-04-08
 */
public enum CustomizeErrorEnums implements InterCustomizeErrorEnums {
    QUESTION_NOT_FOUND("真可惜，修改的记录已经不在了，试着重新发布一条吧！"),
    QUESTION_RECORD_NOT_IN_TABLE("你要的我还没有，但我可以拥有，你可以给我吗？");


    private String message;

    CustomizeErrorEnums(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
