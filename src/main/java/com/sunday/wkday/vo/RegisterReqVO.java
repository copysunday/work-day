package com.sunday.wkday.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterReqVO {
    /**
     * 用户token
     */
    @NotBlank(message = "token不能为空")
    private String userToken;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 2, max = 12, message = "用户名长度不合法")
    private String userName;

    /**
     * 头像地址
     */
    @NotBlank(message = "头像地址不能为空")
    private String avatarUrl;
}
