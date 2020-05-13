package com.sunday.wkday.service;

import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.service.dto.CreateRecordReq;
import com.sunday.wkday.service.dto.GetAllMonthRecordResp;
import com.sunday.wkday.service.dto.GetMonthRecordsResp;

import java.util.List;

public interface WkRecordService {
    /**
     * get list
     * @param projectNo
     * @param wkDate
     * @return
     */
    List<WkRecord> getWkRecord(String projectNo, String wkDate);

    /**
     * 登记
     * @param req
     * @return
     */
    int createRecord(CreateRecordReq req);

    /**
     * 个人月记录
     * @param projectNo
     * @param userId
     * @param year
     * @param month
     * @return
     */
    GetMonthRecordsResp getMonthRecords(String projectNo, String loginUserId, String userId, Integer year, Integer month);

    /**
     *
     * @param projectNo
     * @param year
     * @param month
     * @return
     */
    GetAllMonthRecordResp getAllMonthRecord(String projectNo, String userId, Integer year, Integer month);

}
