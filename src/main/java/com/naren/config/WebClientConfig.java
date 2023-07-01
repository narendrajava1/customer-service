
package com.naren.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import io.netty.resolver.DefaultAddressResolverGroup;
//import reactor.netty.http.client.HttpClient;
//
//
@Configuration
public class WebClientConfig {

	@Bean
//    @Primary
    public WebClient webClient() {
//        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
//	
//	@Bean(name = "webClientConsulAvailability")
//	  public WebClient webClientConsulAvailability(
//	    WebClient.Builder webClientBuilder,
//	    ReactorLoadBalancerExchangeFilterFunction lbFunction,
//	    ExchangeFilterFunction logFilter
//	  ) {
//	    return webClientBuilder
//	      .filter(lbFunction)
//	      .filter(logFilter)
//	      .build();
//	  }
//
}