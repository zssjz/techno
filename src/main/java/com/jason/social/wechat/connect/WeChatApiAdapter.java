package com.jason.social.wechat.connect;

import com.jason.social.wechat.api.WeChatUserInfo;
import com.jason.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeChatApiAdapter implements ApiAdapter<WeChat> {

    private String openId;

    public WeChatApiAdapter() {
    }

    public WeChatApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat weChat) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat weChat, ConnectionValues connectionValues) {
        WeChatUserInfo userInfo = weChat.getUserInfo(openId);
        connectionValues.setProviderUserId(userInfo.getUnionId());
        connectionValues.setImageUrl(userInfo.getHeadImgUrl());
        connectionValues.setDisplayName(userInfo.getNickname());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat weChat) {
        return null;
    }

    @Override
    public void updateStatus(WeChat weChat, String s) {

    }
}
