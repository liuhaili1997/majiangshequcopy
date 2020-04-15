package com.haili.project.projectfirst.enums;
/**
 * 错误码对应的枚举
 * @author Created by hailitortoise on 2020-04-08
 */
public enum CustomizeErrorEnums implements InterCustomizeErrorEnums {
    QUESTION_NOT_FOUND(2001,"真可惜，修改的记录已经不在了，试着重新发布一条吧！"),
    QUESTION_RECORD_NOT_IN_TABLE(2002,"你要的我还没有，但我可以拥有，你可以给我吗？"),

    TARGET_PARAM_NOT_FOUND(2003, "你未对任何问题和结论做出回复"),
    TYPE_PARAM_WRONG(2004, "你的类型不正确，不在判断范围内"),
    COMMENT_NOT_FOUND(2005, "你回复的评论不存在，你出现了幻觉"),
    CONTENT_IS_EMPTY(2006, "你的评论是空的哦，请您啊，知无不言言无不尽！！"),
    READ_NOTIFICATION_FAIL(2007, "已读出错，小兄弟你在偷窥别人信息吖！！"),
    READ_NOTIFICATION_NOT_EXIT(2008, "我查了所有的记录都没有找到相应的记录，不好意思啊！！"),

    NOT_LOGIN(3001,"未登录，你现在做的操作需要等你登录后才有权限操作"),
    SYSTEM_ERROR(3002,"服务端系统异常...我已乏，跪安吧！！！")
    ;

    private Integer code;

    private String message;

    CustomizeErrorEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
