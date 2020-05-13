package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetRecordListReqVO {

    /**
     * 项目ID
     */
    @NotBlank
    private String projectNo;

    /**
     * 工作日
     */
    @NotBlank
    private String wkDate;
}
