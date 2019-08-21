package com.jason.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Created by BNC on 2019/8/20.
 */
public class QQProvider extends AbstractOAuth2ServiceProvider<QQ> {


    public QQProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret,"",""));
    }

    @Override
    public QQ getApi(String s) {
        return null;
    }
}
