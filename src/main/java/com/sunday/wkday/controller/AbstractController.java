package com.sunday.wkday.controller;

import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.enums.BaseErrorCode;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.service.WkUserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
    @Autowired
    private WkUserService wkUserService;

    /**
     * 获取userId，登陆检查
     * @param userToken
     * @return
     * @throws BaseException 未登陆/登陆过期
     */
    protected String getUserId(String userToken) throws BaseException {
        WkUser user = wkUserService.getUser(userToken);
        if (user == null || user.getUserId() == null) {
            throw new BaseException(BaseErrorCode.NOT_LOGIN);
        }
        return user.getUserId();
    }

    protected WkUser getUser(String userToken) throws BaseException {
        WkUser user = wkUserService.getUser(userToken);
        if (user == null || user.getUserId() == null) {
            throw new BaseException(BaseErrorCode.NOT_LOGIN);
        }
        return user;
    }
}
