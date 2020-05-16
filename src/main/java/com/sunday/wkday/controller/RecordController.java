package com.sunday.wkday.controller;

import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.service.WkRecordService;
import com.sunday.wkday.service.WkUserService;
import com.sunday.wkday.service.dto.CreateRecordReq;
import com.sunday.wkday.service.dto.GetAllMonthRecordResp;
import com.sunday.wkday.service.dto.GetMonthRecordsResp;
import com.sunday.wkday.service.dto.RecordDetail;
import com.sunday.wkday.util.NumberUtil;
import com.sunday.wkday.util.ResponseBuilder;
import com.sunday.wkday.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 记录服务
 * @author copysunday
 */
@RequestMapping("/record")
@RestController
public class RecordController extends AbstractController {
    @Autowired
    private WkRecordService wkRecordService;
    @Autowired
    private WkUserService wkUserService;

    /**
     * 记录工时
     * @return
     */
    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Integer> createRecord(@RequestBody @Valid CreateRecordReqVO req) {
        String opUserId = getUserId(req.getUserToken());
        CreateRecordReq createRecordReq = new CreateRecordReq();
        createRecordReq.setOpUserId(opUserId);
        createRecordReq.setProjectNo(req.getProjectNo());
        createRecordReq.setWkDate(req.getWkDate());
        List<RecordDetail> recordDetails = new ArrayList<>();
        createRecordReq.setRecordDetails(recordDetails);
        if (!CollectionUtils.isEmpty(req.getRecordDetails())) {
            for (RecordDetailVO recordDetailVO : req.getRecordDetails()) {
                RecordDetail recordDetail = new RecordDetail();
                recordDetail.setUserId(recordDetailVO.getUserId());
                recordDetail.setRemark(recordDetailVO.getRemark());
                recordDetail.setWkHour(new BigDecimal(recordDetailVO.getWkHour()));
                recordDetails.add(recordDetail);
            }
        }
        int successNum = wkRecordService.createRecord(createRecordReq);
        return ResponseBuilder.success(successNum);
    }

    /**
     * 项目工时详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/getRecordList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<List<RecordVO>> getRecordList(@RequestBody @Valid GetRecordListReqVO req) {
        List<WkRecord> wkRecords = wkRecordService.getWkRecord(req.getProjectNo(), req.getWkDate());
        List<RecordVO> recordList = new ArrayList<>();
        if (CollectionUtils.isEmpty(wkRecords)) {
            return ResponseBuilder.success(recordList);
        }
        List<String> userIdList = wkRecords.stream().map(WkRecord::getUserId).collect(Collectors.toList());
        Map<String, WkUser> userMap = wkUserService.getUserMap(userIdList);
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
            recordList.add(recordVO);
        }
        return ResponseBuilder.success(recordList);
    }

    /**
     * 个人工时月视图数据查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/getMonthRecords", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<GetMonthRecordsRespVO> getMonthRecords(@RequestBody @Valid GetMonthRecordsReqVO req) {
        WkUser wkUser = getUser(req.getUserToken());
        GetMonthRecordsResp monthRecords = wkRecordService.getMonthRecords(req.getProjectNo(), wkUser.getUserId(), req.getUserId(), req.getYear(), req.getMonth());
        GetMonthRecordsRespVO resp = new GetMonthRecordsRespVO();
        resp.setSumMonthHour(monthRecords.getSumMonthHour());
        resp.setHourMap(monthRecords.getHourMap());
        resp.setRemarkMap(monthRecords.getRemarkMap());
        return ResponseBuilder.success(resp);
    }

    /**
     * 所有人工时月视图数据查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/getAllMonthRecord", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<GetAllMonthRecordResp> getAllMonthRecord(@RequestBody @Valid GetAllMonthRecordReqVO req) {
        WkUser wkUser = getUser(req.getUserToken());
        GetAllMonthRecordResp monthRecords = wkRecordService.getAllMonthRecord(req.getProjectNo(), wkUser.getUserId(), req.getYear(), req.getMonth());
        GetAllMonthRecordResp resp = new GetAllMonthRecordResp();
        resp.setWkDateList(monthRecords.getWkDateList());
        resp.setDayRecordDetailLists(monthRecords.getDayRecordDetailLists());
        return ResponseBuilder.success(resp);
    }

}
