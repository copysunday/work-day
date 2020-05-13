package com.sunday.wkday.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WkMemberProjectDetailExt {

    private Long projectId;

    private String projectName;

    private String projectAdmin;

    private String subAdmin;

    /**
     * 项目ID
     */
    private String projectNo;

    private String userId;

    private Date createTime;



}
