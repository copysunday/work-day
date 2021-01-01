package com.sunday.wkday;

import com.sunday.wkday.entity.WkRecordExample;
import com.sunday.wkday.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author sunyangming
 * @date 2021/1/1
 */
public class DateTest {
    @Test
    public void test1() {
        WkRecordExample example = new WkRecordExample();
        WkRecordExample.Criteria criteria = example.createCriteria();
        Date date = DateUtil.parseDate("2020-12-01");
        System.out.println(DateUtil.getDate(date));
        System.out.println(DateUtil.getDate(DateUtils.addMonths(date, 1)));
//
//        criteria.andUserIdEqualTo(req.getQueryUserId())
//                .andProjectNoEqualTo(req.getProjectNo())
//                .andWkDateGreaterThanOrEqualTo()
//                .andWkDateLessThan(DateUtil.getDate(DateUtils.addMonths(date, 1)));
    }
}
