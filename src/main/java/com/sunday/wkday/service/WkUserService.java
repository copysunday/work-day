package com.sunday.wkday.service;

import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.service.dto.LoginResp;
import com.sunday.wkday.service.dto.RegisterUserReq;

import java.util.List;
import java.util.Map;

public interface WkUserService {
    /**
     * 注册用户
     * @param req
     * @return 注册成功返回非null
     */
    boolean register(RegisterUserReq req);

    /**
     * 登陆/重新登陆
     * @param wxCode
     * @return
     */
    LoginResp login(String wxCode);

    /**
     * 用token获取登陆态信息
     * @param userToken
     * @return
     */
    WkUser getUser(String userToken);


    WkUser getUserByUserId(String userId);

    /**
     * user map
     * @param userIdList
     * @return
     */
    Map<String, WkUser> getUserMap(List<String> userIdList);
}
