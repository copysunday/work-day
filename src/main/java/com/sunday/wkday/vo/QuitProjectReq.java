package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuitProjectReq extends BaseReq {

    /**
     * 项目编号
     */
    @NotBlank(message = "项目编号不能为空")
    private String projectNo;

    /**
     * 目标用户
     */
    @NotBlank(message = "targetUserId不能为空")
    private String targetUserId;

}
