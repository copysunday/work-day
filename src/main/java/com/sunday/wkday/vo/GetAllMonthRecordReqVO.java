package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GetAllMonthRecordReqVO {
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

    /**
     * 工作日-年
     */
    @NotNull
    private Integer year;

    /**
     * 工作日-月
     */
    @NotNull
    private Integer month;
}
