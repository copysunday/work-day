package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetProjectDetailReqVO {
    /**
     * 登陆token，换取userId
     */
    @NotBlank(message = "token不能为空")
    private String userToken;

    /**
     * 项目ID
     */
    @NotBlank
    private String projectNo;
}
