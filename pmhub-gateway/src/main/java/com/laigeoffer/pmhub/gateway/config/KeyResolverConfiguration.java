package com.laigeoffer.pmhub.gateway.config;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfiguration {
    @Bean
    public KeyResolver pathKeyResolver(){
        return exchange-> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
