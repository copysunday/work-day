package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Data
public class QueryDiaryReqVO extends BaseReq {


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
