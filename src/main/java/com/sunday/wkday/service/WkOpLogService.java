package com.sunday.wkday.service;

import com.sunday.wkday.entity.WkOpLog;
import com.sunday.wkday.service.dto.AddOpLogReq;

import java.util.List;

public interface WkOpLogService {
    /**
     * 写日志
     * @param req
     * @return
     */
    boolean addOpLog(AddOpLogReq req);

    /**
     *
     * @param projectNo
     * @param minId
     * @param step
     * @return
     */
    List<WkOpLog> getLogList(String projectNo, Long minId, Integer step);

}
