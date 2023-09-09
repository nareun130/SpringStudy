package com.nareun.springboot.learnspringboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//? profiles에서 설정할게 많으면 ConfigurationProperties를 생성
//currency-service.url=
//currency-service.username=
//currency-service.key=
//! profiles와 ConfigurationProperties를 조합해서 쓴다.

@ConfigurationProperties(prefix = "currency-service")
@Component
public class CurrencyServiceConfiguration { //~> 이제 이게 자동으로 프로퍼티에 매핑 된다. -> Controller로 확인

    private String url;
    private String username;
    private String key;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

}
