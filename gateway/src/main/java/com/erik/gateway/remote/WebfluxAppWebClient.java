package com.erik.gateway.remote;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebfluxAppWebClient {

    private final WebClient webClient;

    public WebfluxAppWebClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://webflux-app").build();
    }

    public Mono<String> getHello() {
        return webClient.get().uri("/hello")
            .retrieve().bodyToMono(String.class);
    }
}
