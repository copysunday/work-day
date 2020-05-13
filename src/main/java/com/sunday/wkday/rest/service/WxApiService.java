package com.sunday.wkday.rest.service;

import com.sunday.wkday.rest.model.Code2SessionResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WxApiService {
    private static final String CODE_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";

    private RestTemplate restTemplate;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appSecret;

    WxApiService() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
    }

    /**
     * 登陆获取openid
     * @param jsCode
     * @return
     */
    public Code2SessionResp code2Session(String jsCode) {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("appid", appid);
        reqMap.put("secret", appSecret);
        reqMap.put("js_code", jsCode);
        Code2SessionResp result = restTemplate.getForObject(CODE_URL, Code2SessionResp.class, reqMap);
        log.info("code2Session code:{} result:{}", jsCode, result);
        return result;
    }

    public static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        WxMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
