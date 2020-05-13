package com.sunday.wkday.service.impl;

import com.github.pagehelper.PageHelper;
import com.sunday.wkday.dao.WkOpLogMapper;
import com.sunday.wkday.entity.WkOpLog;
import com.sunday.wkday.entity.WkOpLogExample;
import com.sunday.wkday.service.WkOpLogService;
import com.sunday.wkday.service.dto.AddOpLogReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class WkOpLogServiceImpl implements WkOpLogService {
    @Autowired
    private WkOpLogMapper wkOpLogMapper;

    @Override
    public boolean addOpLog(AddOpLogReq req) {
        try {
            if (StringUtils.isBlank(req.getProjectNo()) || StringUtils.isBlank(req.getOpUserId()) || null == req.getOpType()) {
                throw new IllegalArgumentException("参数不能为空：" + req.toString());
            }
            WkOpLog wkOpLog = new WkOpLog();
            wkOpLog.setCreateTime(new Date());
            wkOpLog.setOpType(req.getOpType().byteValue());
            wkOpLog.setOpUserId(req.getOpUserId());
            wkOpLog.setProjectNo(req.getProjectNo());
            wkOpLog.setRecordNo(req.getRecordNo());
            wkOpLog.setDetail(req.getDetail());
            int res = wkOpLogMapper.insertSelective(wkOpLog);
            return res > 0;
        } catch (Exception e) {
            log.error("addOpLog_fail req:" + req, e);
            return false;
        }

    }

    @Override
    public List<WkOpLog> getLogList(String projectNo, Long minId, Integer step) {
        WkOpLogExample wkOpLogExample = new WkOpLogExample();
        WkOpLogExample.Criteria criteria = wkOpLogExample.createCriteria();
        criteria.andProjectNoEqualTo(projectNo);
        if (minId != null) {
            criteria.andIdLessThan(minId);
        }
        wkOpLogExample.setOrderByClause("id desc");
        PageHelper.startPage(0, step, false);
        List<WkOpLog> wkOpLogs = wkOpLogMapper.selectByExample(wkOpLogExample);
        return wkOpLogs;
    }
}
