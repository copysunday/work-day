package com.sunday.wkday.controller;

import com.sunday.wkday.service.DiaryService;
import com.sunday.wkday.service.dto.CreateProjectReq;
import com.sunday.wkday.util.ResponseBuilder;
import com.sunday.wkday.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@RequestMapping(value = "/diary")
@RestController
public class DiaryController extends AbstractController {
    @Autowired
    private DiaryService diaryService;

    @RequestMapping(value = "/addDiary", method = RequestMethod.POST)
    public BaseResult<Boolean> addDiary(@RequestBody @Valid AddDiaryReqVO req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(diaryService.addDiary(req));
    }

    @RequestMapping(value = "/updateDiary", method = RequestMethod.POST)
    public BaseResult<Boolean> updateDiary(@RequestBody @Valid UpdateDiaryReqVO req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(diaryService.updateDiary(req));
    }

    @RequestMapping(value = "/queryDiary", method = RequestMethod.POST)
    public BaseResult<QueryDiaryRespVO> queryDiary(@RequestBody @Valid QueryDiaryReqVO req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(diaryService.queryDiary(req));
    }
}
