package com.sunday.wkday.vo;

import lombok.Data;

@Data
public class RecordVO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 工时，小时
     */
    private String wkHour;

    /**
     * 比例
     */
    private Float rate;

    /**
     *   头像地址
     */
    private String avatarUrl;

    private Boolean isAdmin;
}
