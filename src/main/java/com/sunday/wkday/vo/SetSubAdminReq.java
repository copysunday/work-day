package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SetSubAdminReq {

    /**
     * 项目编号
     */
    @NotBlank(message = "项目编号不能为空")
    private String projectNo;

    /**
     * 登陆token，换取userId
     */
    @NotBlank(message = "token不能为空")
    private String userToken;

    /**
     * 子管理员
     */
    @NotBlank(message = "不能为空")
    private String subAdmin;

    private Integer adminSetFlag;
}
