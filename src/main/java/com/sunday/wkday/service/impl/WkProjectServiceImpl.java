package com.sunday.wkday.service.impl;

import com.sunday.wkday.dao.WkMemberExtMapper;
import com.sunday.wkday.dao.WkMemberMapper;
import com.sunday.wkday.dao.WkProjectMapper;
import com.sunday.wkday.entity.*;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.service.WkOpLogService;
import com.sunday.wkday.service.WkProjectService;
import com.sunday.wkday.service.WkUserService;
import com.sunday.wkday.service.dto.*;
import com.sunday.wkday.util.DataUtil;
import com.sunday.wkday.util.DateUtil;
import com.sunday.wkday.util.RandomUtil;
import com.sunday.wkday.vo.DeleteProjectReqVO;
import com.sunday.wkday.vo.QuitProjectReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WkProjectServiceImpl implements WkProjectService {
    @Autowired
    private WkProjectMapper wkProjectMapper;
    @Autowired
    private WkOpLogService wkOpLogService;
    @Autowired
    private WkMemberMapper wkMemberMapper;
    @Autowired
    private WkMemberExtMapper wkMemberExtMapper;
    @Autowired
    private WkUserService wkUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createProject(CreateProjectReq req) {
        log.info("createProject：{}", req);
        WkProject wkProject = new WkProject();
        wkProject.setCreateTime(new Date());
        wkProject.setProjectAdmin(req.getUserId());
        wkProject.setProjectName(req.getProjectName());
        wkProject.setProjectNo(RandomUtil.getUUID32());
        int res = wkProjectMapper.insertSelective(wkProject);
        if (res > 0) {
            AddOpLogReq addOpLogReq = new AddOpLogReq();
            addOpLogReq.setProjectNo(wkProject.getProjectNo());
            addOpLogReq.setOpType(1);
            addOpLogReq.setOpUserId(req.getUserId());
            addOpLogReq.setDetail("新建项目[" + req.getProjectName() + "]");
            wkOpLogService.addOpLog(addOpLogReq);
        }
        return res > 0;
    }

    @Override
    public List<ProjectDetail> getProjectList(GetProjectListReq req) {
        List<WkMemberProjectExt> wkProjects = wkMemberExtMapper.selectProjectDetail(req.getUserId(), req.getMinId(), req.getStep());
        List<ProjectDetail> projectDetailList = new ArrayList<>();
        if (CollectionUtils.isEmpty(wkProjects)) {
            return projectDetailList;
        }
        List<String> projectNoList = wkProjects.stream().map(WkMemberProjectExt::getProjectNo).collect(Collectors.toList());
        List<WkMemberExt> wkMemberExtList = wkMemberExtMapper.selectBatchMemberDetail(projectNoList);

        for (WkMemberProjectExt wkProject : wkProjects) {
            List<MemberDetail> memberDetails = wkMemberExtList.stream()
                    .filter(o -> wkProject.getProjectNo().equals(o.getProjectNo()))
                    .map(o -> {
                        MemberDetail memberDetail = new MemberDetail();
                        memberDetail.setAvatarUrl(o.getAvatarUrl());
                        memberDetail.setUserId(o.getUserId());
                        memberDetail.setUserName(o.getUserName());
                        return memberDetail;
                    }).collect(Collectors.toList());
            ProjectDetail projectDetail = new ProjectDetail();
            projectDetail.setId(wkProject.getProjectId());
            projectDetail.setProjectAdmin(wkProject.getProjectAdmin());
            projectDetail.setProjectName(wkProject.getProjectName());
            projectDetail.setProjectNo(wkProject.getProjectNo());
            projectDetail.setMemberDetails(memberDetails);
            projectDetail.setIsAdmin(req.getUserId().equals(wkProject.getProjectAdmin()));
            projectDetail.setIsSubAdmin(DataUtil.checkAdmin(req.getUserId(), wkProject.getProjectAdmin(), wkProject.getSubAdmin()));
            projectDetailList.add(projectDetail);
        }
        return projectDetailList;
    }


    @Override
    public ProjectDetail getProjectDetail(String userId, String projectNo) {
        List<WkMemberProjectDetailExt> wkProjects = wkMemberExtMapper.selectProjectDetailByNo(projectNo);
        if (CollectionUtils.isEmpty(wkProjects)) {
            return null;
        }
        List<WkMemberProjectDetailExt> wkProjectReal = wkProjects.stream().filter(o -> o.getUserId() != null && !o.getUserType().equals((byte) 2))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(wkProjectReal)) {
            return null;
        }
        List<MemberDetail> memberDetails = new ArrayList<>();
        List<String> userIdList = wkProjectReal.stream()
                .filter(o -> o.getUserId() !=null && o.getUserType().equals((byte) 0))
                .map(WkMemberProjectDetailExt::getUserId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userIdList)) {
            Map<String, WkUser> userMap = wkUserService.getUserMap(userIdList);
            for (WkMemberProjectDetailExt wkProject : wkProjectReal) {
                WkUser wkUser = userMap.get(wkProject.getUserId());
                if (wkUser != null) {
                    MemberDetail memberDetail = new MemberDetail();
                    memberDetail.setAvatarUrl(wkUser.getAvatarUrl());
                    memberDetail.setUserId(wkUser.getUserId());
                    memberDetail.setUserName(wkUser.getUserName());
                    memberDetails.add(memberDetail);
                }
            }
        }

        WkMemberProjectDetailExt wkProject = wkProjectReal.get(0);
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setProjectAdmin(wkProject.getProjectAdmin());
        projectDetail.setProjectName(wkProject.getProjectName());
        projectDetail.setProjectNo(wkProject.getProjectNo());
        projectDetail.setMemberDetails(memberDetails);
        projectDetail.setCreateTime(DateUtil.getDateTime(wkProject.getCreateTime()));
        // 管理员信息
        WkUser user = wkUserService.getUserByUserId(wkProject.getProjectAdmin());
        projectDetail.setAdminName(user.getUserName());
        projectDetail.setAdminAvatarUrl(user.getAvatarUrl());
        projectDetail.setIsAdmin(userId.equals(projectDetail.getProjectAdmin()));
        projectDetail.setIsSubAdmin(DataUtil.checkAdmin(userId, wkProject.getProjectAdmin(), wkProject.getSubAdmin()));
        projectDetail.setIsMember(userIdList.contains(userId));
        projectDetail.setAdminList(DataUtil.getAdminList(wkProject.getProjectAdmin(), wkProject.getSubAdmin()));
        return projectDetail;
    }

    @Override
    public WkProject getProject(String projectNo) {
        WkProjectExample wkProjectExample = new WkProjectExample();
        WkProjectExample.Criteria criteria = wkProjectExample.createCriteria();
        criteria.andProjectNoEqualTo(projectNo);
        List<WkProject> wkProjects = wkProjectMapper.selectByExample(wkProjectExample);
        return CollectionUtils.isEmpty(wkProjects) ? null : wkProjects.get(0);
    }

    @Override
    public boolean joinProject(JoinProjectReq req) {
        // 检查参数
        WkProject project = getProject(req.getProjectNo());
        if (project == null) {
            throw new BaseException("项目不存在：" + req.getProjectNo());
        }

        WkMember wkMember = new WkMember();
        wkMember.setCreateTime(new Date());
        wkMember.setProjectNo(req.getProjectNo());
        wkMember.setUserId(req.getUserId());
        wkMember.setUserType((byte)0);
        try {
            int res = wkMemberMapper.insertSelective(wkMember);
            if (res > 0) {
                AddOpLogReq addOpLogReq = new AddOpLogReq();
                addOpLogReq.setProjectNo(wkMember.getProjectNo());
                addOpLogReq.setOpType(1);
                addOpLogReq.setOpUserId(req.getUserId());
                addOpLogReq.setDetail(req.getUserName() + " 加入项目[" + project.getProjectName() + "]");
                wkOpLogService.addOpLog(addOpLogReq);
            }
            return res > 0;
        } catch (DuplicateKeyException e) {
            log.info("重新加入项目：{}", req);
            WkMember wkMemberUpdate = new WkMember();
            wkMemberUpdate.setUserType((byte) 0);
            WkMemberExample wkMemberExample = new WkMemberExample();
            wkMemberExample.createCriteria()
                    .andProjectNoEqualTo(req.getProjectNo())
                    .andUserIdEqualTo(req.getUserId());
            return wkMemberMapper.updateByExampleSelective(wkMemberUpdate, wkMemberExample) > 0;
        }
    }

    @Override
    public boolean quitProject(QuitProjectReq req) {
        WkProject project = getProject(req.getProjectNo());
        if (project == null) {
            throw new BaseException("项目不存在：" + req.getProjectNo());
        }
        String targetUserId;
        byte targetUserType;
        if (StringUtils.isBlank(req.getTargetUserId())) {
            targetUserId = req.getUserId();
            targetUserType = 2;
        } else {
            if (!project.getProjectAdmin().equals(req.getUserId())) {
                throw new BaseException("非管理员不允许操作");
            }
            targetUserId = req.getTargetUserId();
            targetUserType = 1;
        }
        WkMember wkMember = new WkMember();
        wkMember.setUserType(targetUserType);
        WkMemberExample wkMemberExample = new WkMemberExample();
        wkMemberExample.createCriteria().andProjectNoEqualTo(req.getProjectNo())
                .andUserIdEqualTo(targetUserId);
        boolean res = wkMemberMapper.updateByExampleSelective(wkMember, wkMemberExample) > 0;
        if (res) {
            WkUser user = wkUserService.getUserByUserId(targetUserId);
            String desc = targetUserType == 2 ? user.getUserName() + " 退出项目" : "管理员把[" + user.getUserName() + "]移出项目";
            AddOpLogReq addOpLogReq = new AddOpLogReq();
            addOpLogReq.setProjectNo(req.getProjectNo());
            addOpLogReq.setOpType(2);
            addOpLogReq.setOpUserId(req.getUserId());
            addOpLogReq.setDetail(desc);
            wkOpLogService.addOpLog(addOpLogReq);
        }
        return res;
    }

    @Override
    public boolean deleteProject(DeleteProjectReqVO req) {
        WkProjectExample wkProjectExample = new WkProjectExample();
        WkProjectExample.Criteria criteria = wkProjectExample.createCriteria();
        criteria.andProjectNoEqualTo(req.getProjectNo());

        WkProject wkProject = new WkProject();
        wkProject.setProjectStatus((byte) -1);
        boolean res = wkProjectMapper.updateByExampleSelective(wkProject, wkProjectExample) > 0;
        if (res) {
            AddOpLogReq addOpLogReq = new AddOpLogReq();
            addOpLogReq.setProjectNo(req.getProjectNo());
            addOpLogReq.setOpType(3);
            addOpLogReq.setOpUserId(req.getUserId());
            addOpLogReq.setDetail(req.getUserName() + " 删除项目");
            wkOpLogService.addOpLog(addOpLogReq);
        }
        return res;
    }

    @Override
    public boolean setSubAdmin(String userId, String projectNo, String subAdmin, Integer adminSetFlag) {
        boolean isAdd = adminSetFlag != null && adminSetFlag == 1;
        // 检查参数
        WkProject project = getProject(projectNo);
        if (project == null) {
            throw new BaseException("项目不存在");
        }
        if (!project.getProjectAdmin().equals(userId)) {
            throw new BaseException("非管理员不允许操作");
        }
        Set<String> subAdminSet = new HashSet<>(Arrays.asList(project.getSubAdmin().split(",")));
        if (isAdd) {
            if (subAdminSet.contains(subAdmin)) {
                return true;
            }
            if (subAdminSet.size() >= 5) {
                throw new BaseException("最多添加5个子管理员");
            }
            subAdminSet.add(subAdmin);
        } else {
            subAdminSet.remove(subAdmin);
        }
        WkProjectExample wkProjectExample = new WkProjectExample();
        WkProjectExample.Criteria criteria = wkProjectExample.createCriteria();
        criteria.andProjectNoEqualTo(projectNo);

        WkProject wkProject = new WkProject();
        wkProject.setSubAdmin(String.join(",", subAdminSet));
        log.info("wkProject:{}", wkProject);
        return wkProjectMapper.updateByExampleSelective(wkProject, wkProjectExample) > 0;
    }

}
