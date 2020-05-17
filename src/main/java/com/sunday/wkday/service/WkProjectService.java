package com.sunday.wkday.service;

import com.sunday.wkday.entity.WkProject;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.service.dto.CreateProjectReq;
import com.sunday.wkday.service.dto.GetProjectListReq;
import com.sunday.wkday.service.dto.JoinProjectReq;
import com.sunday.wkday.service.dto.ProjectDetail;
import com.sunday.wkday.vo.QuitProjectReq;

import java.util.List;

public interface WkProjectService {
    /**
     * 创建项目
     * @param req
     * @return
     */
    boolean createProject(CreateProjectReq req);

    /**
     * 查询项目列表
     * @param req
     * @return
     */
    List<ProjectDetail> getProjectList(GetProjectListReq req);

    /**
     * 查询项目
     * @param projectNo
     * @return
     */
    ProjectDetail getProjectDetail(String userId, String projectNo);

    WkProject getProject(String projectNo);

    /**
     * 加入项目
     * @param req
     * @return
     */
    boolean joinProject(JoinProjectReq req);

    boolean quitProject(QuitProjectReq req);


    boolean setSubAdmin(String userId, String projectNo, String subAdmin, Integer adminSetFlag);
}
