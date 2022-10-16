package com.erik.gateway.remote;

import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "webflux-app")
public interface WebfluxAppReactiveFeignClient {

    @GetMapping("/hello")
    Mono<String> getHello();
}
