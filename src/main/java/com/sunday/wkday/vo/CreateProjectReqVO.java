package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateProjectReqVO {
    /**
     * 项目名
     */
    @NotBlank(message = "项目名不能为空")
    private String projectName;

    /**
     * 创建者
     */
    @NotBlank(message = "token不能为空")
    private String userToken;
}
