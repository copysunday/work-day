package com.sunday.wkday.service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author sunyangming
 * @date 2020/5/4
 */
@Data
public class GetAllMonthRecordResp {

    private List<String> wkDateList;

    private List<List<DayRecordDetail>> dayRecordDetailLists;

}
