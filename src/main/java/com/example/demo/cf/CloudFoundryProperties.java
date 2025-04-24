package com.example.demo.cf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cf")
class CloudFoundryProperties {

    private String apiHost;
    private Integer apiPort = 443;
    private boolean secure = true;
    private boolean skipSslValidation = false;

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public Integer getApiPort() {
        return apiPort;
    }

    public void setApiPort(Integer apiPort) {
        this.apiPort = apiPort;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public boolean isSkipSslValidation() {
        return skipSslValidation;
    }

    public void setSkipSslValidation(boolean skipSslValidation) {
        this.skipSslValidation = skipSslValidation;
    }
}
