package com.jason.social.wechat.connect;

import com.jason.social.wechat.api.WeChat;
import com.jason.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    public static final String WE_CHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect";

    public static final String WE_CHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatServiceProvider(String clientId, String clientSecret) {
        super(new OAuth2Template(clientId, clientSecret, WE_CHAT_AUTHORIZE_URL, WE_CHAT_ACCESS_TOKEN_URL));
    }

    @Override
    public WeChat getApi(String s) {
        return new WeChatImpl(s);
    }
}
