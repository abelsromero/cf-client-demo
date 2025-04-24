package com.example.demo.cf;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.tokenprovider.ClientCredentialsGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UaaConfiguration {

    @Bean
    ReactorUaaClient uaaClient(ConnectionContext connectionContext,
                               ClientCredentialsGrantTokenProvider tokenProvider) {
        return ReactorUaaClient.builder()
                .connectionContext(connectionContext)
                .tokenProvider(tokenProvider)
                .build();
    }

    @Bean
    ClientCredentialsGrantTokenProvider tokenProvider(UaaProperties properties) {
        return ClientCredentialsGrantTokenProvider
                .builder()
                .clientId(properties.clientId())
                .clientSecret(properties.clientSecret())
                .build();
    }

}
