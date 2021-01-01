package com.sunday.wkday.service.impl;

import com.sunday.wkday.dao.WkDiaryMapper;
import com.sunday.wkday.entity.WkDiary;
import com.sunday.wkday.entity.WkDiaryExample;
import com.sunday.wkday.entity.WkRecord;
import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.service.DiaryService;
import com.sunday.wkday.service.dto.DayRecordDetail;
import com.sunday.wkday.util.DateUtil;
import com.sunday.wkday.util.RandomUtil;
import com.sunday.wkday.vo.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private WkDiaryMapper wkDiaryMapper;

    @Override
    public Boolean addDiary(AddDiaryReqVO req) {
        WkDiary wkDiary = new WkDiary();
        wkDiary.setDiaryNo(RandomUtil.getUUID32());
        wkDiary.setDiary(req.getDiary());
        wkDiary.setCreateTime(new Date());
        wkDiary.setProjectNo(req.getProjectNo());
        wkDiary.setWkDate(req.getWkDate());
        wkDiary.setUserId(req.getUserId());
        return wkDiaryMapper.insertSelective(wkDiary) > 0;
    }

    @Override
    public Boolean updateDiary(UpdateDiaryReqVO req) {
        WkDiary wkDiary = new WkDiary();
        wkDiary.setDiary(req.getDiary());

        WkDiaryExample wkDiaryExample = new WkDiaryExample();
        wkDiaryExample.createCriteria().andUserIdEqualTo(req.getUserId())
                .andDiaryNoEqualTo(req.getDiaryNo());
        return wkDiaryMapper.updateByExampleSelective(wkDiary, wkDiaryExample) > 0;
    }

    @Override
    public QueryDiaryRespVO queryDiary(QueryDiaryReqVO req) {
        Date date = DateUtil.parseDate(req.getYear() + "-" + req.getMonth() + "-01");
        WkDiaryExample wkDiaryExample = new WkDiaryExample();
        WkDiaryExample.Criteria criteria = wkDiaryExample.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId())
                .andProjectNoEqualTo(req.getProjectNo())
                .andWkDateGreaterThanOrEqualTo(DateUtil.getDate(date))
                .andWkDateLessThan(DateUtil.getDate(DateUtil.addMonths(date, 1)));
        wkDiaryExample.setOrderByClause("wk_date desc");

        QueryDiaryRespVO resp = new QueryDiaryRespVO();
        Map<Integer, String> diaryMap = new HashMap<>();
        List<WkDiary> wkDiaries = wkDiaryMapper.selectByExample(wkDiaryExample);
        if (CollectionUtils.isEmpty(wkDiaries)) {
            resp.setWkDateList(new ArrayList<>());
            resp.setDiaryLists(new ArrayList<>());
            resp.setDiaryMap(diaryMap);
        }
        List<String> wkDateList = wkDiaries.stream()
                .map(WkDiary::getWkDate)
                .distinct()
                .collect(Collectors.toList());

        List<List<DiaryDetailVO>> dayRecordDetailLists = wkDateList.stream().map(wkDate -> wkDiaries.stream()
                .filter(o -> wkDate.equals(o.getWkDate()))
                .map(wkDiary -> {
                    DiaryDetailVO diaryDetailVO = new DiaryDetailVO();
                    diaryDetailVO.setDiary(wkDiary.getDiary());
                    diaryDetailVO.setDiaryNo(wkDiary.getDiaryNo());
                    diaryDetailVO.setWkDate(wkDiary.getWkDate());
                    diaryDetailVO.setCreateTime(DateUtil.getSimpleTime(wkDiary.getCreateTime()));
                    return diaryDetailVO;
                }).collect(Collectors.toList())
        ).collect(Collectors.toList());

        dayRecordDetailLists.forEach(list -> {
            DiaryDetailVO diaryDetailVO = list.get(0);
            int day = Integer.parseInt(diaryDetailVO.getWkDate().substring(8));
            diaryMap.put(day, diaryDetailVO.getDiary());
        });

        resp.setDiaryLists(dayRecordDetailLists);
        resp.setWkDateList(wkDateList);
        resp.setDiaryMap(diaryMap);
        return resp;
    }
}
