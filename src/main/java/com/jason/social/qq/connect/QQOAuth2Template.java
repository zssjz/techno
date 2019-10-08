package com.jason.social.qq.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String result = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.debug("获取accessToken响应： {}", result);
        String[] item = result.split("&");
        String accessToken = item[0].substring(item[0].lastIndexOf("="));
        Long expiresIn = new Long(item[0].substring(item[0].lastIndexOf("=")));
        String refreshToken = item[0].substring(item[0].lastIndexOf("="));
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
