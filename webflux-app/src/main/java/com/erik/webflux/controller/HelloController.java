package com.erik.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/hello")
    Mono<String> hello() throws InterruptedException {
        Thread.sleep(1000);
        return Mono.just("hello");
    }
}
