package com.sunday.wkday.service.impl;

import com.alibaba.fastjson.JSON;
import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.service.WkRecordService;
import com.sunday.wkday.service.dto.GetAllMonthRecordResp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WkRecordServiceImplTest {
    @Autowired
    private WkRecordService wkRecordService;

    @Test
    void testMonthRecord() {
//        GetAllMonthRecordResp allMonthRecord = wkRecordService.getAllMonthRecord("70317c86883243438f2638cb4eb8acbe", 2020, 1);
//        System.out.println(JSON.toJSONString(allMonthRecord));
    }
}