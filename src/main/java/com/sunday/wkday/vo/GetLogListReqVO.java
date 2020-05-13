package com.sunday.wkday.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetLogListReqVO {
    /**
     * 项目ID
     */
    @NotBlank
    private String projectNo;

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

    /**
     * 操作人token
     */
    @NotBlank
    private String userToken;
}
