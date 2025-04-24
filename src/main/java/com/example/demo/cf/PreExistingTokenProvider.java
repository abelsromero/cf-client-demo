package com.example.demo.cf;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
class PreExistingTokenProvider implements TokenProvider {

    @Override
    public Mono<String> getToken(ConnectionContext connectionContext) {
        return Mono.just("");
    }
}
