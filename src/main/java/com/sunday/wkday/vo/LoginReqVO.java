package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVO {
    /**
     * 微信联登code
     */
    @NotBlank(message = "code不能为空")
    private String wxCode;
}
