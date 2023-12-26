package com.thisisaniceteam.chat.common.client.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NaverSecret {
    @Value("${naver.client_id}")
    private String client_id;

    @Value("${naver.client_secret}")
    private String client_secret;

    @Value("${naver.redirect_uri}")
    private String redirect_uri;

    @Value("${naver.state}")
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getState() {
        return state;
    }
}
