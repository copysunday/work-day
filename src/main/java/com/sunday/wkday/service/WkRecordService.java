package com.sunday.wkday.service;

import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.service.dto.CreateRecordReq;
import com.sunday.wkday.service.dto.GetAllMonthRecordResp;
import com.sunday.wkday.service.dto.GetMonthRecordsReq;
import com.sunday.wkday.service.dto.GetMonthRecordsResp;
import com.sunday.wkday.vo.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
     * 工时列表
     * @param req
     * @return
     */
    List<RecordVO> getRecordList(GetRecordListReqVO req);

    /**
     * 个人月记录
     * @return
     */
    GetMonthRecordsRespVO getMonthRecords(GetMonthRecordsReq req);

    /**
     *
     * @param projectNo
     * @param year
     * @param month
     * @return
     */
    GetAllMonthRecordResp getAllMonthRecord(String projectNo, String userId, Integer year, Integer month);

}
