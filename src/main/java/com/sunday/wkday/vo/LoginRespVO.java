package com.sunday.wkday.vo;

import lombok.Data;

@Data
public class LoginRespVO {
    /**
     * 是否已注册
     */
    private Boolean hasRegister;

    private String userToken;

    private Long expireTime;
}
