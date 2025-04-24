package com.example.demo.cf;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Optional;

// Notes: we use Uaa Token provider for everything to simulate a service-broker
@Configuration
@EnableConfigurationProperties({CloudFoundryProperties.class, UaaProperties.class})
class CloudFoundryConfiguration {

//    @Bean
//    CloudFoundryProperties properties() {
//        CloudFoundryProperties properties = new CloudFoundryProperties();
//        properties.setApiHost("api.sys.dhaka.cf-app.com");
//        properties.setSkipSslValidation(true);
//        return properties;
//    }

    @Bean
    DefaultConnectionContext connectionContext(CloudFoundryProperties properties) {
        return DefaultConnectionContext
                .builder()
                .apiHost(properties.getApiHost())
                .port(Optional.ofNullable(properties.getApiPort()))
                .secure(properties.isSecure())
                .skipSslValidation(properties.isSkipSslValidation())
                .connectTimeout(Duration.ofMinutes(1))
                .sslHandshakeTimeout(Duration.ofMinutes(1))
                .build();
    }

    @Bean
    ReactorCloudFoundryClient cloudFoundryClient(ConnectionContext connectionContext,
                                                 TokenProvider tokenProvider) {
        return ReactorCloudFoundryClient
                .builder()
                .connectionContext(connectionContext)
                .tokenProvider(tokenProvider)
                .build();
    }

}
