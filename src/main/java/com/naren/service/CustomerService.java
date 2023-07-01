package com.naren.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	WebClient webClient; 
	
	@Autowired
    private EurekaClient eurekaClient;
	
	String urlForGetOrders=null;
	
	@Retry(name = "GET_CUSTOMER",fallbackMethod = "getDefaultCustomer")
	public String getCustomer() {
		logger.info("in get customer");
		Applications applications = eurekaClient.getApplications();
		System.out.println(applications);
		applications.getRegisteredApplications().forEach(application->{
			application.getInstances().forEach(instanceInfo->{
				if(instanceInfo.getAppName().equalsIgnoreCase("ORDER-SERVICE")){
					urlForGetOrders = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort()+"/api/orders"+ "/" + "get-order";
				}
			});
		});
		
		
		
//		urlForCreateOrders=baseUrlForOrders+ "/" + "create-order";
//		 Flux accounts= webClientBuilder.build().get().uri("http://accountservice/customer/{customer}", id).retrieve().bodyToFlux(Account.class);
		Mono<String> bodyToMono = webClient.get().uri(urlForGetOrders).retrieve()
        .bodyToMono(String.class);
		logger.info("end get customer {}",bodyToMono.block());
		return "customer get succssfully"; 
	}
	
	
	public String getDefaultCustomer( RuntimeException re) {
		logger.info("in getDefaultCustomer");
		return "default customer succssfully"; 
	}
	
	

}
