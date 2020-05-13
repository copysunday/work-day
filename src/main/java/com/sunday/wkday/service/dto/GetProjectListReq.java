package com.sunday.wkday.service.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetProjectListReq {
    /**
     * userId
     */
    private String userId;

    /**
     * 上一次查询返回的minId
     */
    private Long minId;

    /**
     * 步长
     */
    private Integer step;
}
