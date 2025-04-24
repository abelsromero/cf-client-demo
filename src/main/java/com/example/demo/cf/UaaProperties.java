package com.example.demo.cf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cf.uaa")
record UaaProperties(String clientId, String clientSecret) {
}
