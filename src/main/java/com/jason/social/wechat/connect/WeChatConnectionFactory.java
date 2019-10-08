package com.jason.social.wechat.connect;

import com.jason.social.wechat.api.WeChat;
import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String providerId, String appId, String secret) {
        super(providerId, new WeChatServiceProvider(appId, secret), new WeChatApiAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
//        return super.extractProviderUserId(accessGrant);
        if (accessGrant instanceof WeChatAccessGrant) {
            return ((WeChatAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
//        return super.createConnection(accessGrant);
        return new OAuth2Connection<WeChat>(
                getProviderId(),
                extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(),
                accessGrant.getExpireTime(),
                (OAuth2ServiceProvider<WeChat>) getServiceProvider(),
                new WeChatApiAdapter(extractProviderUserId(accessGrant))
        );
    }

    @Override
    public Connection<WeChat> createConnection(ConnectionData data) {
//        return super.createConnection(data);
        return new OAuth2Connection<WeChat>(
                data,
                (OAuth2ServiceProvider<WeChat>) getServiceProvider(),
                new WeChatApiAdapter(data.getProviderId())
        );
    }
}
