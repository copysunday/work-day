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

        return ResponseBuilder.success(wkRecordService.getRecordList(req));
    }

    /**
     * 个人工时月视图数据查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/getMonthRecords", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<GetMonthRecordsRespVO> getMonthRecords(@RequestBody @Valid GetMonthRecordsReqVO req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(wkRecordService.getMonthRecords(req));
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
