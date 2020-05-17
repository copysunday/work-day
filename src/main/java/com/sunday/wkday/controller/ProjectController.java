package com.sunday.wkday.controller;


import com.sunday.wkday.entity.WkOpLog;
import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.enums.BaseErrorCode;
import com.sunday.wkday.service.WkOpLogService;
import com.sunday.wkday.service.WkProjectService;
import com.sunday.wkday.service.dto.CreateProjectReq;
import com.sunday.wkday.service.dto.GetProjectListReq;
import com.sunday.wkday.service.dto.JoinProjectReq;
import com.sunday.wkday.service.dto.ProjectDetail;
import com.sunday.wkday.util.DateUtil;
import com.sunday.wkday.util.ResponseBuilder;
import com.sunday.wkday.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 项目服务
 * @author copysunday
 */
@RequestMapping(value = "/pj")
@RestController
public class ProjectController extends AbstractController {
    @Autowired
    private WkProjectService wkProjectService;
    @Autowired
    private WkOpLogService wkOpLogService;

    /**
     * 创建项目
     * @param req
     * @return
     */
    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public BaseResult<Boolean> createProject(@RequestBody @Valid CreateProjectReqVO req) {
        String userId = getUserId(req.getUserToken());
        CreateProjectReq createProjectReq = new CreateProjectReq();
        createProjectReq.setUserId(userId);
        createProjectReq.setProjectName(req.getProjectName());
        boolean result = wkProjectService.createProject(createProjectReq);
        return ResponseBuilder.success(result);
    }

    /**
     * 加入项目
     * @param req 请求model
     * @return
     */
    @RequestMapping(value = "/joinProject", method = RequestMethod.POST)
    public BaseResult<Boolean> joinProject(@RequestBody @Valid JoinProjectReqVO req) {
        WkUser user = getUser(req.getUserToken());
        JoinProjectReq joinProjectReq = new JoinProjectReq();
        joinProjectReq.setProjectNo(req.getProjectNo());
        joinProjectReq.setUserId(user.getUserId());
        joinProjectReq.setUserName(user.getUserName());
        boolean result = wkProjectService.joinProject(joinProjectReq);
        return ResponseBuilder.success(result);
    }

    @RequestMapping(value = "/setSubAdmin", method = RequestMethod.POST)
    public BaseResult<Boolean> setSubAdmin(@RequestBody @Valid SetSubAdminReq req) {
        String userId = getUserId(req.getUserToken());
        boolean result = wkProjectService.setSubAdmin(userId, req.getProjectNo(), req.getSubAdmin(), req.getAdminSetFlag());
        return ResponseBuilder.success(result);
    }

    @RequestMapping(value = "/quitProject", method = RequestMethod.POST)
    public BaseResult<Boolean> quitProject(@RequestBody @Valid QuitProjectReq req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(wkProjectService.quitProject(req));
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public BaseResult<Boolean> deleteProject(@RequestBody @Valid DeleteProjectReqVO req) {
        req.setUserId(getUserId(req.getUserToken()));
        return ResponseBuilder.success(wkProjectService.deleteProject(req));
    }

    /**
     * 查询个人所有项目
     * @param req
     * @return
     */
    @RequestMapping(value = "/getProjectList", method = RequestMethod.POST)
    public BaseResult<GetProjectListRespVO> getProjectList(@RequestBody @Valid GetProjectListReqVO req) {
        String userId = getUserId(req.getUserToken());
        GetProjectListReq getProjectListReq = new GetProjectListReq();
        getProjectListReq.setMinId(req.getMinId());
        getProjectListReq.setStep(req.getStep());
        getProjectListReq.setUserId(userId);
        List<ProjectDetail> projectList = wkProjectService.getProjectList(getProjectListReq);
        GetProjectListRespVO result = new GetProjectListRespVO();
        result.setProjectDetails(projectList);
        Long minId = CollectionUtils.isEmpty(projectList) ? null :
                projectList.stream().map(ProjectDetail::getId).min(Long::compare).get();
        result.setMinId(minId);
        return ResponseBuilder.success(result);
    }

    /**
     * 获取项目详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/getProjectDetail", method = RequestMethod.POST)
    public BaseResult<ProjectDetail> getProjectDetail(@RequestBody @Valid GetProjectDetailReqVO req) {
        String userId = getUserId(req.getUserToken());
        ProjectDetail projectDetail = wkProjectService.getProjectDetail(userId, req.getProjectNo());
        if (projectDetail == null) {
            ResponseBuilder.error(BaseErrorCode.PROJECT_NOT_EXIST);
        }
        return ResponseBuilder.success(projectDetail);
    }

    /**
     * 查询项目操作日志列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/getLogList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<List<GetLogListRespVO>> getLogList(@RequestBody @Valid GetLogListReqVO req) {
        List<WkOpLog> logList = wkOpLogService.getLogList(req.getProjectNo(), req.getMinId(), req.getStep());
        if (CollectionUtils.isEmpty(logList)) {
            ResponseBuilder.success(new ArrayList<>());
        }
        List<GetLogListRespVO> getLogListRespVOList = new ArrayList<>();
        Map<String, List<GetLogListRespVO.LogDetail>> logDetailMap = new HashMap<>();
        for (WkOpLog wkOpLog: logList) {
            String simpleDate = DateUtil.getSimpleDate(wkOpLog.getCreateTime());
            List<GetLogListRespVO.LogDetail> logDetails = logDetailMap.computeIfAbsent(simpleDate, o -> new ArrayList<>());
            String simpleTime = DateUtil.getSimpleTime(wkOpLog.getCreateTime());
            GetLogListRespVO.LogDetail logDetail = new GetLogListRespVO.LogDetail();
            logDetail.setDetail(wkOpLog.getDetail());
            logDetail.setSimpleTime(simpleTime);
            logDetails.add(logDetail);
        }
        Long minId = CollectionUtils.isEmpty(logList) ? null :
                logList.stream().map(WkOpLog::getId).min(Long::compare).get();
        for (WkOpLog wkOpLog: logList) {
            String simpleDate = DateUtil.getSimpleDate(wkOpLog.getCreateTime());
            List<GetLogListRespVO.LogDetail> logDetails = logDetailMap.get(simpleDate);
            if (logDetails == null) {
                continue;
            }
            GetLogListRespVO vo = new GetLogListRespVO();
            vo.setMinId(minId);
            vo.setCount(logList.size());
            vo.setSimpleDate(simpleDate);
            vo.setLogDetails(logDetails);
            getLogListRespVOList.add(vo);
            logDetailMap.put(simpleDate, null);
        }
        return ResponseBuilder.success(getLogListRespVOList);
    }
}
