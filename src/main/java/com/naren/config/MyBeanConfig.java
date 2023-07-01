//package com.naren.config;
//
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class MyBeanConfig {
//
// @Bean
// WebClient webClient(LoadBalancerClient lbClient) {
//    return WebClient.builder()
////             .filter(new LoadBalancerExchangeFilterFunction(lbClient))
//             .build();
//    }
//
//}