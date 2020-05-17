package com.sunday.wkday.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class GetMonthRecordsReqVO extends BaseReq {
    /**
     * 查询的userId
     */
    @NotBlank(message = "uid不能为空")
    private String queryUserId;
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
