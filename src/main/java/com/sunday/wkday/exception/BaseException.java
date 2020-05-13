package com.sunday.wkday.exception;

import com.sunday.wkday.enums.BaseErrorCode;

/**
 * @author copysunday
 */
public class BaseException extends RuntimeException {
    /**
     * 异常错误码
     */
    protected String code;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode.getMessage());
        this.code = baseErrorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
