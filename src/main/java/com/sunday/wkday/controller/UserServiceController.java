package com.sunday.wkday.controller;

import com.sunday.wkday.enums.BaseErrorCode;
import com.sunday.wkday.service.WkUserService;
import com.sunday.wkday.service.dto.LoginResp;
import com.sunday.wkday.service.dto.RegisterUserReq;
import com.sunday.wkday.util.ResponseBuilder;
import com.sunday.wkday.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户服务
 * @author copysunday
 */
@RequestMapping(value = "/user")
@RestController
public class UserServiceController extends AbstractController {
    @Autowired
    private WkUserService userService;

    /**
     * 注册登记
     * @param req
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResult<Boolean> register(@RequestBody @Valid RegisterReqVO req) {
        String userId = getUserId(req.getUserToken());
        RegisterUserReq registerUserReq = new RegisterUserReq();
        registerUserReq.setUserId(userId);
        registerUserReq.setUserName(req.getUserName());
        registerUserReq.setAvatarUrl(req.getAvatarUrl());
        boolean result = userService.register(registerUserReq);
        return ResponseBuilder.success(result);
    }

    /**
     * 登陆、过期重新登陆
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO req) {
        LoginResp loginResp = userService.login(req.getWxCode());
        if (!loginResp.getRequestSuccess()) {
            return ResponseBuilder.error(BaseErrorCode.LOGIN_FAIL);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setHasRegister(loginResp.getHasRegister());
        loginRespVO.setExpireTime(loginResp.getExpireTime());
        loginRespVO.setUserToken(loginResp.getUserToken());
        return ResponseBuilder.success(loginRespVO);
    }

}
