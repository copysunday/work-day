package com.sunday.wkday.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WkMemberProjectExt {

    private Long projectId;

    private String projectName;

    private String projectAdmin;

    private String subAdmin;

    /**
     * 项目ID
     */
    private String projectNo;
}
