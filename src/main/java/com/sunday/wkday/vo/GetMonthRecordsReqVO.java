package com.sunday.wkday.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class GetMonthRecordsReqVO {
    /**
     * 操作人token
     */
    @NotBlank
    private String userToken;
    /**
     * 查询的userId
     */
    @NotBlank
    private String userId;
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
