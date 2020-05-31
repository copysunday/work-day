package com.sunday.wkday.service.dto;

import lombok.Data;


@Data
public class GetMonthRecordsReq {

    private String userId;
    /**
     * 查询的userId
     */
    private String queryUserId;
    /**
     * 项目ID
     */
    private String projectNo;

    /**
     * 工作日-年
     */
    private Integer year;

    /**
     * 工作日-月
     */
    private Integer month;
}
