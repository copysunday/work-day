package com.sunday.wkday.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDetail {

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 管理员uid
     */
    private String projectAdmin;

    /**
     * 管理员
     */
    private String adminName;

    /**
     * 管理员头像
     */
    private String adminAvatarUrl;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否管理员
     */
    private Boolean isAdmin;

    /**
     * 是否子管理员
     */
    private Boolean isSubAdmin;

    /**
     * 是否为成员
     */
    private Boolean isMember;

    /**
     * 项目状态
     */
    private Integer projectStatus;

    /**
     * id
     */
    private Long id;

    /**
     * 项目成员列表
     */
    private List<MemberDetail> memberDetails;
    /**
     * 管理员，子管理员列表
     */
    private List<String> adminList;
}
