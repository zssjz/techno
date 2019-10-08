package com.jason.social.qq.connect;

import com.jason.social.qq.api.QQ;
import com.jason.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    public static final String QQ_AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";

    public static final String QQ_ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQ_AUTHORIZE_URL, QQ_ACCESS_TOKEN_URL));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String s) {
        return new QQImpl(s, appId);
    }
}
