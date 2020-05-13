package com.sunday.wkday.service.dto;

import lombok.Data;

@Data
public class CreateProjectReq {
    /**
     * 项目名
     */
    private String projectName;

    /**
     * 创建者
     */
    private String userId;
}
