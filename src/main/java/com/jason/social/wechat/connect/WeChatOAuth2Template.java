package com.jason.social.wechat.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

public class WeChatOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    public static final String WE_CHAT_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    @Autowired
    private ObjectMapper objectMapper;

    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        String authorizeUrl = super.buildAuthorizeUrl(parameters);
        authorizeUrl += "?appid=" + clientId + "&scope=snsapi_login";
        return authorizeUrl;
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
//        return super.buildAuthenticateUrl(parameters);
        return buildAuthenticateUrl(parameters);
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
//        return super.exchangeForAccess(authorizationCode, redirectUri, additionalParameters);
        StringBuilder stringBuilder = new StringBuilder(accessTokenUrl);
        stringBuilder.append("?appid=" + clientId);
        stringBuilder.append("&secret=" + clientSecret);
        stringBuilder.append("&code=" + authorizationCode);
        stringBuilder.append("&grant_type=authorization_code");
        return getAccessToken(stringBuilder);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
//        return super.refreshAccess(refreshToken, additionalParameters);
        StringBuilder stringBuilder = new StringBuilder(WE_CHAT_REFRESH_TOKEN_URL);
        stringBuilder.append("?appid=" + clientId);
        stringBuilder.append("&grant_type=refresh_token");
        stringBuilder.append("&refresh_token=" + refreshToken);
        return getAccessToken(stringBuilder);
    }

    @Override
    protected RestTemplate getRestTemplate() {
        RestTemplate restTemplate = super.getRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    private AccessGrant getAccessToken(StringBuilder stringBuilder) {

        Map<String, String> response = getRestTemplate().getForObject(stringBuilder.toString(), Map.class);

        if (response.containsKey("errcode")) {
            throw new RuntimeException("accessToken获取失败，errcode: " + response.get("errcode") + "，errmsg：" + response.get("errmsg"));
        }

        WeChatAccessGrant accessGrant = new WeChatAccessGrant(
                response.get("access_token"),
                response.get("scope"),
                response.get("refresh_token"),
                Long.valueOf(response.get("accessToken"))
        );

        accessGrant.setOpenId(response.get("openid"));
        accessGrant.setUnionId(response.get("unionid"));

        return accessGrant;

    }
}
