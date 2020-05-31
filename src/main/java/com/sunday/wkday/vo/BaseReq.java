package com.sunday.wkday.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunyangming
 * @date 2020/5/16
 */
@Data
public class BaseReq {
    /**
     * 操作人token
     */
    @NotBlank
    private String userToken;
    /**
     * 登陆后写入
     */
    private String userId;
    /**
     * 登陆后写入
     */
    private String userName;

}
