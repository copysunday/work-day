package com.sunday.wkday.rest.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author copysunday
 */
@Data
public class Code2SessionResp {

    private String openid;

    @JSONField(name = "session_key")
    private String sessionKey;

    /**
     * 0 成功
     */
    private String errcode;

    private String errmsg;
}
