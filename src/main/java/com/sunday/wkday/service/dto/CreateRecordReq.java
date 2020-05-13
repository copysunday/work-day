package com.sunday.wkday.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRecordReq {

    private String projectNo;

    /**
     * 工作日 e.g 2019-12-10
     */
    private String wkDate;

    /**
     * 操作人
     */
    private String opUserId;

    private List<RecordDetail> recordDetails;
}
