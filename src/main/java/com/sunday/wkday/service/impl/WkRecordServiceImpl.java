package com.sunday.wkday.service.impl;

import com.google.common.base.Objects;
import com.sunday.wkday.dao.WkRecordMapper;
import com.sunday.wkday.entity.*;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.service.WkOpLogService;
import com.sunday.wkday.service.WkProjectService;
import com.sunday.wkday.service.WkRecordService;
import com.sunday.wkday.service.WkUserService;
import com.sunday.wkday.service.dto.*;
import com.sunday.wkday.util.*;
import com.sunday.wkday.vo.GetMonthRecordsReqVO;
import com.sunday.wkday.vo.GetMonthRecordsRespVO;
import com.sunday.wkday.vo.GetRecordListReqVO;
import com.sunday.wkday.vo.RecordVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author copysunday
 */
@Service
public class WkRecordServiceImpl implements WkRecordService {
    @Autowired
    private WkRecordMapper wkRecordMapper;
    @Autowired
    private WkOpLogService wkOpLogService;
    @Autowired
    private WkUserService wkUserService;
    @Autowired
    private WkProjectService wkProjectService;

    @Override
    public List<WkRecord> getWkRecord(String projectNo, String wkDate) {
        WkRecordExample wkRecordExample = new WkRecordExample();
        WkRecordExample.Criteria criteria = wkRecordExample.createCriteria();
        criteria.andProjectNoEqualTo(projectNo);
        criteria.andWkDateEqualTo(wkDate);
        return wkRecordMapper.selectByExample(wkRecordExample);
    }

    @Override
    public int createRecord(CreateRecordReq req) {
        if (CollectionUtils.isEmpty(req.getRecordDetails())) {
            return 0;
        }
        WkProject project = wkProjectService.getProject(req.getProjectNo());
        if (project == null) {
            throw new BaseException("项目不存在：" + req.getProjectNo());
        }
        if (!DataUtil.checkAdmin(req.getOpUserId(), project.getProjectAdmin(), project.getSubAdmin())) {
            throw new BaseException("非管理员无权操作");
        }

        List<String> userIdList = req.getRecordDetails().stream().map(RecordDetail::getUserId).collect(Collectors.toList());
        userIdList.add(req.getOpUserId());
        Map<String, WkUser> userMap = wkUserService.getUserMap(userIdList);

        WkUser adminUser = userMap.get(req.getOpUserId());

        int successNum = 0;
        for (RecordDetail recordDetail : req.getRecordDetails()) {
            WkUser wkUser = userMap.get(recordDetail.getUserId());
            WkRecord wkRecord = getWkRecord(recordDetail.getUserId(), req.getProjectNo(), req.getWkDate());
            AddOpLogReq addOpLogReq = new AddOpLogReq();
            addOpLogReq.setOpUserId(req.getOpUserId());
            addOpLogReq.setProjectNo(req.getProjectNo());
            if (wkRecord == null) {
                WkRecord wkRecordAdd = new WkRecord();
                wkRecordAdd.setRecordNo(RandomUtil.getUUID32());
                wkRecordAdd.setCreateTime(new Date());
                wkRecordAdd.setOpUserId(req.getOpUserId());
                wkRecordAdd.setProjectNo(req.getProjectNo());
                wkRecordAdd.setUserId(recordDetail.getUserId());
                wkRecordAdd.setWkHour(recordDetail.getWkHour());
                wkRecordAdd.setRemark(recordDetail.getRemark());
                wkRecordAdd.setWkDate(req.getWkDate());
                int res = wkRecordMapper.insertSelective(wkRecordAdd);
                if (res > 0) {
                    successNum++;
                    addOpLogReq.setOpType(1);
                    addOpLogReq.setRecordNo(wkRecordAdd.getRecordNo());
                    addOpLogReq.setDetail(String.format("%s 新增[%s]%s工时-[%s]小时", adminUser.getUserName(), wkUser.getUserName(), req.getWkDate().substring(5), recordDetail.getWkHour()));
                    wkOpLogService.addOpLog(addOpLogReq);
                }
            } else {
                WkRecordExample example = new WkRecordExample();
                WkRecordExample.Criteria criteria = example.createCriteria();
                criteria.andUserIdEqualTo(recordDetail.getUserId())
                        .andProjectNoEqualTo(req.getProjectNo())
                        .andWkDateEqualTo(req.getWkDate());
                WkRecord wkRecordUpdate = new WkRecord();
                wkRecordUpdate.setWkHour(recordDetail.getWkHour());
                wkRecordUpdate.setRemark(recordDetail.getRemark());
                int res = wkRecordMapper.updateByExampleSelective(wkRecordUpdate, example);
                if (res > 0) {
                    successNum++;
                    if (wkRecord.getWkHour().compareTo(recordDetail.getWkHour()) != 0) {
                        addOpLogReq.setOpType(1);
                        addOpLogReq.setRecordNo(wkRecord.getRecordNo());
                        addOpLogReq.setDetail(String.format("%s 修改[%s]%s工时-旧[%s]新[%s]小时", adminUser.getUserName(), wkUser.getUserName(),
                                req.getWkDate().substring(5), wkRecord.getWkHour(), recordDetail.getWkHour()));
                        wkOpLogService.addOpLog(addOpLogReq);
                    }
                }
            }
        }
        return successNum;
    }

    @Override
    public List<RecordVO> getRecordList(GetRecordListReqVO req) {

        WkProject project = wkProjectService.getProject(req.getProjectNo());
        List<String> adminList = DataUtil.getAdminList(project.getProjectAdmin(), project.getSubAdmin());

        List<WkRecord> wkRecords = getWkRecord(req.getProjectNo(), req.getWkDate());
        List<RecordVO> recordList = new ArrayList<>();
        if (CollectionUtils.isEmpty(wkRecords)) {
            return recordList;
        }
        List<String> userIdList = wkRecords.stream().map(WkRecord::getUserId).collect(Collectors.toList());
        Map<String, WkUser> userMap = wkUserService.getUserMap(userIdList);

        List<RecordVO> normalRecordList = new ArrayList<>();

        for (WkRecord wkRecord: wkRecords) {
            RecordVO recordVO = new RecordVO();
            WkUser wkUser = userMap.get(wkRecord.getUserId());
            if (wkUser != null) {
                recordVO.setAvatarUrl(wkUser.getAvatarUrl());
                recordVO.setUserName(wkUser.getUserName());
            }
            recordVO.setUserId(wkRecord.getUserId());
            recordVO.setRemark(wkRecord.getRemark());
            recordVO.setWkHour(NumberUtil.formatNum(wkRecord.getWkHour(), 1));
            long wkHour = wkRecord.getWkHour().longValue();
            Float rate = wkHour >= 8 ? 100f : wkHour / 8f * 100;
            recordVO.setRate(rate);
            boolean isAdmin = adminList.contains(wkRecord.getUserId());
            recordVO.setIsAdmin(isAdmin);
            if (isAdmin) {
                recordList.add(recordVO);
            } else {
                normalRecordList.add(recordVO);
            }
        }
        recordList.addAll(normalRecordList);
        return recordList;
    }

    @Override
    public GetMonthRecordsRespVO getMonthRecords(GetMonthRecordsReq req) {
        WkRecordExample example = new WkRecordExample();
        WkRecordExample.Criteria criteria = example.createCriteria();
        Date date = DateUtil.parseDate(req.getYear() + "-" + req.getMonth() + "-01");
        criteria.andUserIdEqualTo(req.getQueryUserId())
                .andProjectNoEqualTo(req.getProjectNo())
                .andWkDateGreaterThanOrEqualTo(DateUtil.getDate(date))
                .andWkDateLessThan(DateUtil.getDate(DateUtil.addMonths(date, 1)));
        List<WkRecord> wkRecords = wkRecordMapper.selectByExample(example);

        GetMonthRecordsRespVO resp = new GetMonthRecordsRespVO();
        if (CollectionUtils.isEmpty(wkRecords)) {
            resp.setSumMonthHour("0");
            resp.setHourMap(new HashMap<>());
            return resp;
        }

        WkProject wkProject = wkProjectService.getProject(req.getProjectNo());
        boolean isAdmin = DataUtil.checkAdmin(req.getUserId(), wkProject.getProjectAdmin(), wkProject.getSubAdmin());

        Map<Integer, String> hourMap = new HashMap<>();
        Map<Integer, String> remarkMap = new HashMap<>();
        BigDecimal count = new BigDecimal("0");
        for (WkRecord wkRecord : wkRecords) {
            int day = Integer.parseInt(wkRecord.getWkDate().substring(8));
            if (BigDecimal.ZERO.compareTo(wkRecord.getWkHour()) < 0) {
                hourMap.put(day, NumberUtil.formatNum(wkRecord.getWkHour(), 1));
                count = count.add(wkRecord.getWkHour());
            }
            if (StringUtils.isNotBlank(wkRecord.getRemark()) && (isAdmin || req.getUserId().equals(wkRecord.getUserId()))) {
                remarkMap.put(day, wkRecord.getRemark());
            }
        }
        resp.setUserId(req.getUserId());
        resp.setHourMap(hourMap);
        resp.setRemarkMap(remarkMap);
        resp.setSumMonthHour(NumberUtil.formatNum(count, 1));
        return resp;
    }

    @Override
    public GetAllMonthRecordResp getAllMonthRecord(String projectNo, String userId, Integer year, Integer month) {
        WkRecordExample example = new WkRecordExample();
        WkRecordExample.Criteria criteria = example.createCriteria();
        Date date = DateUtil.parseDate(year + "-" + month + "-01");
        criteria.andProjectNoEqualTo(projectNo)
                .andWkDateGreaterThanOrEqualTo(DateUtil.getDate(date))
                .andWkDateLessThan(DateUtil.getDate(DateUtil.addMonths(date, 1)));
        example.setOrderByClause("user_id asc");
        List<WkRecord> wkRecords = wkRecordMapper.selectByExample(example);
        GetAllMonthRecordResp resp = new GetAllMonthRecordResp();

        if (CollectionUtils.isEmpty(wkRecords)) {
            resp.setWkDateList(new ArrayList<>());
            resp.setDayRecordDetailLists(new ArrayList<>());
            return resp;
        }

        WkProject wkProject = wkProjectService.getProject(projectNo);
        boolean isAdmin = DataUtil.checkAdmin(userId, wkProject.getProjectAdmin(), wkProject.getSubAdmin());

        List<String> userIdList = wkRecords.stream()
                .map(WkRecord::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<String, WkUser> userMap = wkUserService.getUserMap(new ArrayList<>(userIdList));


        List<String> wkDateList = wkRecords.stream()
                .map(WkRecord::getWkDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<List<DayRecordDetail>> dayRecordDetailLists = wkDateList.stream().map(wkDate -> wkRecords.stream()
                .filter(o -> wkDate.equals(o.getWkDate()))
                .map(wkRecord -> {
                    DayRecordDetail dayRecordDetail = new DayRecordDetail();
                    WkUser wkUser = userMap.get(wkRecord.getUserId());
                    if (wkUser != null) {
                        dayRecordDetail.setAvatarUrl(wkUser.getAvatarUrl());
                        dayRecordDetail.setUserName(wkUser.getUserName());
                    }
                    dayRecordDetail.setUserId(wkRecord.getUserId());
                    dayRecordDetail.setWkDate(wkRecord.getWkDate());
                    if (isAdmin || userId.equals(wkRecord.getUserId())) {
                        dayRecordDetail.setRemark(wkRecord.getRemark());
                    }
                    dayRecordDetail.setWkHour(wkRecord.getWkHour().toPlainString());
                    return dayRecordDetail;
                }).collect(Collectors.toList())
        ).collect(Collectors.toList());

        Map<String, List<WkRecord>> listMap = wkRecords.stream().collect(Collectors.groupingBy(WkRecord::getUserId));
        List<DayRecordDetail> sumDayRecordDetailList = new ArrayList<>();
        listMap.forEach((uid, v) -> {
            BigDecimal wkHour = v.stream()
                    .map(WkRecord::getWkHour)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);

            DayRecordDetail dayRecordDetail = new DayRecordDetail();
            WkUser wkUser = userMap.get(uid);
            if (wkUser != null) {
                dayRecordDetail.setAvatarUrl(wkUser.getAvatarUrl());
                dayRecordDetail.setUserName(wkUser.getUserName());
            }
            dayRecordDetail.setUserId(uid);
            dayRecordDetail.setWkDate(month + "月");
            dayRecordDetail.setWkHour(wkHour.toPlainString());
            sumDayRecordDetailList.add(dayRecordDetail);
        });

        wkDateList.add(0, month + "月总计");
        dayRecordDetailLists.add(0, sumDayRecordDetailList);

        resp.setWkDateList(wkDateList);
        resp.setDayRecordDetailLists(dayRecordDetailLists);
        return resp;
    }

    private WkRecord getWkRecord(String userId, String projectNo, String wkDate) {
        WkRecordExample example = new WkRecordExample();
        WkRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId)
                .andProjectNoEqualTo(projectNo)
                .andWkDateEqualTo(wkDate);
        List<WkRecord> wkRecords = wkRecordMapper.selectByExample(example);
        return CollectionUtils.isEmpty(wkRecords) ? null : wkRecords.get(0);
    }
}
