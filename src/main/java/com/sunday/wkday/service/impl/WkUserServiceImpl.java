package com.sunday.wkday.service.impl;

import com.sunday.wkday.constants.CommonConstants;
import com.sunday.wkday.dao.WkUserMapper;
import com.sunday.wkday.entity.WkUser;
import com.sunday.wkday.entity.WkUserExample;
import com.sunday.wkday.rest.model.Code2SessionResp;
import com.sunday.wkday.rest.service.WxApiService;
import com.sunday.wkday.service.WkUserService;
import com.sunday.wkday.service.dto.LoginResp;
import com.sunday.wkday.service.dto.RegisterUserReq;
import com.sunday.wkday.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WkUserServiceImpl implements WkUserService {
    @Autowired
    private WkUserMapper wkUserMapper;
    @Autowired
    private WxApiService wxApiService;

    @Override
    public boolean register(RegisterUserReq req) {
        WkUser user = new WkUser();
        user.setUserName(req.getUserName());
        user.setAvatarUrl(req.getAvatarUrl());
        // where
        WkUserExample wkUserExample = new WkUserExample();
        WkUserExample.Criteria criteria = wkUserExample.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        int result = wkUserMapper.updateByExampleSelective(user, wkUserExample);
        return result > 0;
    }

    private Date getExpireTime(int days) {
        return new Date(System.currentTimeMillis() + days * 1000L * 24 * 3600);
    }

    @Override
    public LoginResp login(String wxCode) {

        LoginResp loginResp = new LoginResp();
        loginResp.setRequestSuccess(false);
        // 微信登陆
        Code2SessionResp code2SessionResp = wxApiService.code2Session(wxCode);
        if (code2SessionResp == null || StringUtils.isBlank(code2SessionResp.getOpenid())) {
            return loginResp;
        }
        String openId = code2SessionResp.getOpenid();
        // 查询用户
        WkUser wkUserOld = getByOpenId(code2SessionResp.getOpenid());
        // 已经登陆过，自动续期
        if (wkUserOld != null) {
            WkUserExample wkUserExample = new WkUserExample();
            WkUserExample.Criteria criteria = wkUserExample.createCriteria();
            criteria.andOpenIdEqualTo(openId);
            WkUser wkUser = new WkUser();
            wkUser.setUserToken(RandomUtil.getUUID32());
            wkUser.setExpireTime(getExpireTime(CommonConstants.EXPIRE_DAYS));
            int result = wkUserMapper.updateByExampleSelective(wkUser, wkUserExample);
            loginResp.setRequestSuccess(result > 0);
            loginResp.setExpireTime(wkUser.getExpireTime().getTime());
            loginResp.setUserToken(wkUser.getUserToken());
            // 登记了姓名才为注册
            loginResp.setHasRegister(StringUtils.isNotBlank(wkUserOld.getUserName()));
            return loginResp;
        }
        // 新注册
        WkUser user = new WkUser();
        user.setUserId(RandomUtil.getUUID32());
        user.setCreateTime(new Date());
        user.setOpenId(openId);
        user.setUserToken(RandomUtil.getUUID32());
        user.setExpireTime(getExpireTime(CommonConstants.EXPIRE_DAYS));
        int result = wkUserMapper.insertSelective(user);
        loginResp.setRequestSuccess(result > 0);
        loginResp.setExpireTime(user.getExpireTime().getTime());
        loginResp.setUserToken(user.getUserToken());
        loginResp.setHasRegister(false);
        return loginResp;
    }

    @Override
    public WkUser getUser(String userToken) {
        WkUserExample wkUserExample = new WkUserExample();
        WkUserExample.Criteria criteria = wkUserExample.createCriteria();
        criteria.andUserTokenEqualTo(userToken)
                .andExpireTimeGreaterThanOrEqualTo(new Date());
        List<WkUser> wkUsers = wkUserMapper.selectByExample(wkUserExample);
        return CollectionUtils.isEmpty(wkUsers) ? null : wkUsers.get(0);
    }

    @Override
    public WkUser getUserByUserId(String userId) {
        WkUserExample wkUserExample = new WkUserExample();
        WkUserExample.Criteria criteria = wkUserExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<WkUser> wkUsers = wkUserMapper.selectByExample(wkUserExample);
        return CollectionUtils.isEmpty(wkUsers) ? null : wkUsers.get(0);
    }

    private WkUser getByOpenId(String openId) {
        WkUserExample wkUserExample = new WkUserExample();
        WkUserExample.Criteria criteria = wkUserExample.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<WkUser> wkUsers = wkUserMapper.selectByExample(wkUserExample);
        return CollectionUtils.isEmpty(wkUsers) ? null : wkUsers.get(0);
    }

    @Override
    public Map<String, WkUser> getUserMap(List<String> userIdList) {
        WkUserExample wkUserExample = new WkUserExample();
        wkUserExample.createCriteria().andUserIdIn(userIdList);
        List<WkUser> wkUsers = wkUserMapper.selectByExample(wkUserExample);
        Map<String, WkUser> userMap = new HashMap<>();
        wkUsers.forEach(o -> userMap.put(o.getUserId(), o));
        return userMap;
    }
}
