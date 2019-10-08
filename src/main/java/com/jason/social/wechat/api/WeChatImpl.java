package com.jason.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    @Autowired
    private ObjectMapper objectMapper;

    public static final String GET_WE_CHAT_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    public WeChatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }


    @Override
    public WeChatUserInfo getUserInfo(String openId) {
        String url = String.format(GET_WE_CHAT_USER_INFO_URL, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        if (result.contains("errcode")) {
            return null;
        }
        WeChatUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, WeChatUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return userInfo;
    }

    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = super.getMessageConverters();
        converters.remove(0);
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return converters;
    }
}
