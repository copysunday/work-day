package com.sunday.wkday.service;

import com.sunday.wkday.vo.*;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
public interface DiaryService {
    Boolean addDiary(AddDiaryReqVO req);
    Boolean updateDiary(UpdateDiaryReqVO req);
    QueryDiaryRespVO queryDiary(QueryDiaryReqVO req);
}
