package com.sunday.wkday.service.impl;

import com.alibaba.fastjson.JSON;
import com.sunday.wkday.controller.AbstractController;
import com.sunday.wkday.dao.WkMemberExtMapper;
import com.sunday.wkday.entity.WkMemberExt;
import com.sunday.wkday.entity.WkMemberProjectExt;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.service.WkProjectService;
import com.sunday.wkday.service.dto.CreateProjectReq;
import com.sunday.wkday.service.dto.GetProjectListReq;
import com.sunday.wkday.service.dto.JoinProjectReq;
import com.sunday.wkday.service.dto.ProjectDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WkProjectServiceImplTest extends AbstractController {
    @Autowired
    private WkProjectService wkProjectService;
    @Autowired
    private WkMemberExtMapper wkMemberExtMapper;

    @Test
    void testMember() {
        List<WkMemberExt> res = wkMemberExtMapper.selectMemberDetail("acf3a6b780f54948a0aec08d15594d33");
        System.out.println(res);
        ArrayList<String> list = new ArrayList<>();
        list.add("acf3a6b780f54948a0aec08d15594d33");
        list.add("48c2907e58554ab78dc06b80592caaf5");
        System.out.println(wkMemberExtMapper.selectBatchMemberDetail(list));
    }

    @Test
    void testPro() {
        List<WkMemberProjectExt> wkMemberProjectExts = wkMemberExtMapper.selectProjectDetail("9d3ce6d7b9a142a0b3dc20bfe4e3e024", 1000L, 10);
        System.out.println(wkMemberProjectExts);
    }

    @Test
    void testCreate() {
        CreateProjectReq createProjectReq = new CreateProjectReq();
        createProjectReq.setUserId("131232");
        createProjectReq.setProjectName("测试项目2");
        wkProjectService.createProject(createProjectReq);
    }

    @Test
    void testJoin() throws BaseException {
        JoinProjectReq joinProjectReq = new JoinProjectReq();
        joinProjectReq.setProjectNo("pj123");
        joinProjectReq.setUserId("2342342343");
        boolean res = wkProjectService.joinProject(joinProjectReq);
        System.out.println(res);
    }

    @Test
    void testList() {
        GetProjectListReq getProjectListReq = new GetProjectListReq();
        getProjectListReq.setStep(10);
        getProjectListReq.setMinId(null);
        getProjectListReq.setUserId("9d3ce6d7b9a142a0b3dc20bfe4e3e024");
        List<ProjectDetail> projectList = wkProjectService.getProjectList(getProjectListReq);
        System.out.println(JSON.toJSONString(projectList));
    }

    @Test
    void getPjDetail() {
        // {"userToken":"16613479542f4b189f87b910e00a4497","projectNo":"ac6a88068f3a4eeaa32f5b1e5dfa6386"}
        String userId = getUserId("16613479542f4b189f87b910e00a4497");
        ProjectDetail projectDetail = wkProjectService.getProjectDetail(userId,"ac6a88068f3a4eeaa32f5b1e5dfa6386");
        System.out.println(projectDetail);
    }
}
