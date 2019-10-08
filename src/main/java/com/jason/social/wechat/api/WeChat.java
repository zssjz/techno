package com.jason.social.wechat.api;

public interface WeChat {

    /**
     * 获取用户信息
     * @return
     */
    WeChatUserInfo getUserInfo(String openId);

}
