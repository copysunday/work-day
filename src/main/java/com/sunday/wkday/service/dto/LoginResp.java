package com.sunday.wkday.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginResp {
    /**
     * 登陆成功 true
     */
    private Boolean requestSuccess;

    /**
     * 是否已注册
     */
    private Boolean hasRegister;

    private String userToken;

    private Long expireTime;
}
