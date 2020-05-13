package com.sunday.wkday.vo;

import lombok.Data;

@Data
public class BaseResult<T> {
    /**
     * 错误码，0000为正常
     */
    private String code;
    /**
     * 说明
     */
    private String message;

    private T data;

    public BaseResult() {}

    public BaseResult(T data) {
        this.data = data;
    }
}
