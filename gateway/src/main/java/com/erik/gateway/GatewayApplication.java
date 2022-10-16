package com.erik.gateway;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
//@EnableFeignClients
@EnableReactiveFeignClients
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public Decoder feignDecoder() {

		ObjectFactory<HttpMessageConverters> messageConverters = () -> {
			HttpMessageConverters converters = new HttpMessageConverters();
			return converters;
		};
		return new SpringDecoder(messageConverters);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
}
