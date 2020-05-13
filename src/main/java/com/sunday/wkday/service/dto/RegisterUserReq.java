package com.sunday.wkday.service.dto;

import lombok.Data;

@Data
public class RegisterUserReq {

    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像地址
     */
    private String avatarUrl;
}
