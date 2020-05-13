package com.sunday.wkday.vo;

import com.sunday.wkday.service.dto.ProjectDetail;
import lombok.Data;

import java.util.List;

@Data
public class GetProjectListRespVO {

    /**
     * 项目列表
     */
    private List<ProjectDetail> projectDetails;

    /**
     * 查询返回的minId
     */
    private Long minId;
}
