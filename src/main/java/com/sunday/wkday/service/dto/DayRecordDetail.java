package com.sunday.wkday.service.dto;

import lombok.Data;

/**
 * @author sunyangming
 * @date 2020/5/4
 */
@Data
public class DayRecordDetail {

    private String wkHour;

    private String wkDate;

    private String userId;

    private String remark;

    /**
     * 用户名
     */
    private String userName;

    /**
     *   头像地址
     */
    private String avatarUrl;
}
