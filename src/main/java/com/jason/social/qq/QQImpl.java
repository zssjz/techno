package com.jason.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * Created by BNC on 2019/8/20.
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    @Override
    public QQUserInfo getUserInfo() {
        return null;
    }
}
