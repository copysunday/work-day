package com.sunday.wkday.enums;

import lombok.Getter;

/**
 * 错误码
 * @author copysunday
 */
public enum BaseErrorCode {

    SUCCESS("0000", "success"),
    UNKNOWN_ERROR("4001", "系统繁忙，请稍后再试"),
    METHOD_NOT_SUPPORTED("4002", "MethodNotSupported"),
    PARAM_ERROR("4003", "参数错误"),

    NOT_LOGIN("B001", "未登陆/登陆过期"),
    LOGIN_WX_FAIL("B002", "微信登陆失败"),
    LOGIN_FAIL("B003", "登陆失败"),
    PROJECT_NOT_EXIST("B004", "项目不存在"),

    ;

    @Getter
    private String code;

    @Getter
    private String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
