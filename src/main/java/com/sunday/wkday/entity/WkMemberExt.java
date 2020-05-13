package com.sunday.wkday.entity;

import lombok.Data;

@Data
public class WkMemberExt {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 项目ID
     */
    private String projectNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     *   头像地址
     */
    private String avatarUrl;

    public WkMemberExt(String userId, String projectNo, String userName, String avatarUrl) {
        this.userId = userId;
        this.projectNo = projectNo;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }
}
