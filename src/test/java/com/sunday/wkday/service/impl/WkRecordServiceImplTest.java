package com.sunday.wkday.service.impl;

import com.alibaba.fastjson.JSON;
import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.service.DiaryService;
import com.sunday.wkday.service.WkRecordService;
import com.sunday.wkday.service.dto.GetAllMonthRecordResp;
import com.sunday.wkday.service.dto.GetMonthRecordsReq;
import com.sunday.wkday.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WkRecordServiceImplTest {
    @Autowired
    private WkRecordService wkRecordService;
    @Autowired
    private DiaryService diaryService;

    @Test
    void testMonthRecord() {
//        GetAllMonthRecordResp allMonthRecord = wkRecordService.getAllMonthRecord("70317c86883243438f2638cb4eb8acbe", 2020, 1);
//        System.out.println(JSON.toJSONString(allMonthRecord));
    }

    @Test
    void testDiary() {
        AddDiaryReqVO addDiaryReqVO = new AddDiaryReqVO();
        addDiaryReqVO.setUserId("123");
        addDiaryReqVO.setDiary("hello 333 44444");
        addDiaryReqVO.setProjectNo("1233333");
        addDiaryReqVO.setWkDate("2020-05-14");
        Boolean aBoolean = diaryService.addDiary(addDiaryReqVO);
        System.out.println(aBoolean);
    }

    @Test
    void testQuery() {
        QueryDiaryReqVO queryDiaryReqVO = new QueryDiaryReqVO();
        queryDiaryReqVO.setUserId("123");
        queryDiaryReqVO.setYear(2020);
        queryDiaryReqVO.setMonth(5);
        queryDiaryReqVO.setProjectNo("1233333");
        QueryDiaryRespVO queryDiaryRespVO = diaryService.queryDiary(queryDiaryReqVO);
        System.out.println(queryDiaryRespVO);
    }

    @Test
    void testMonth() {
        String s = "{\"userToken\":\"2479c9e44f9642ef9e2cc7b4aa2bafa6\",\"userId\":\"2d018a15eaa14ee3b81f1e7d2ae211fb\",\"year\":\"2020\",\"month\":\"5\",\"projectNo\":\"19de4b2a6a144ff885be341231d628d6\"}";
        GetMonthRecordsReq getMonthRecordsReqVO = JSON.parseObject(s, GetMonthRecordsReq.class);
        GetMonthRecordsRespVO monthRecords = wkRecordService.getMonthRecords(getMonthRecordsReqVO);
        System.out.println(monthRecords);
    }
}