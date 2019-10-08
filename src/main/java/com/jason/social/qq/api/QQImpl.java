package com.jason.social.qq.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.util.Map;

/**
 * 多实例对象
 * Created by BNC on 2019/8/20.
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    public static final String GET_QQ_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    public static final String GET_QQ_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(GET_QQ_OPENID_URL, accessToken);
        Map<String, String> result = getRestTemplate().getForObject(url, Map.class);
        this.openId = result.get("openid");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(GET_QQ_USER_INFO_URL, appId, openId);
        QQUserInfo userInfo = getRestTemplate().getForObject(url, QQUserInfo.class);
        userInfo.setOpenId(openId);
        return userInfo;
    }
}
