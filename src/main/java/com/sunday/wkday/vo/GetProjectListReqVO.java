package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetProjectListReqVO {
    /**
     * 登陆token，换取userId
     */
    @NotBlank(message = "token不能为空")
    private String userToken;

    /**
     * 上一次查询返回的minId
     */
    private Long minId;

    /**
     * 步长
     */
    @Min(value = 0)
    @Max(value = 100)
    private Integer step;
}
