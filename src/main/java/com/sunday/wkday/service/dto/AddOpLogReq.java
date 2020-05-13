package com.sunday.wkday.service.dto;

import lombok.Data;

@Data
public class AddOpLogReq {

    private String opUserId;

    private String projectNo;

    private String recordNo;

    /**
     * Database Column Remarks:
     *   1新增 2修改 3删除
     */
    private Integer opType;

    private String detail;
}
