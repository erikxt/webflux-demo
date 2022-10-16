package com.erik.gateway.filter;

import com.erik.gateway.remote.WebfluxAppWebClient;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CommonFilter implements GlobalFilter, Ordered {

    //@Resource
    //private WebfluxAppFeignClient webfluxAppFeignClient;

    //@Resource
    //private WebfluxAppReactiveFeignClient webfluxAppReactiveFeignClient;

    @Resource
    private WebfluxAppWebClient webClient;


    @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        CompletableFuture<String> completableFuture =
            CompletableFuture.supplyAsync(() -> webClient.getHello().block(Duration.ofSeconds(5)));
        try {
            log.info("print {}", completableFuture.get(6 ,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return chain.filter(exchange);
    }

    @Override public int getOrder() {
        return 0;
    }
}
