package com.naren.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.naren.service.CustomerService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

import reactor.core.publisher.Mono;

@RestController
public class CustomerController {
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	 private String baseUrlForOrders = "http://order-service/api/orders";
	
	@Autowired
	WebClient webClient; 
	
	@Autowired
    private EurekaClient eurekaClient;
	
	String urlForCreateOrders=null;
	String urlForGetOrders=null;
	
	@Autowired
	private CustomerService customerService;
	@PostMapping("/create-customer")
	public String createCustomer() {
		logger.info("in create customer");
		Applications applications = eurekaClient.getApplications();
		System.out.println(applications);
		applications.getRegisteredApplications().forEach(application->{
			application.getInstances().forEach(instanceInfo->{
				if(instanceInfo.getAppName().equalsIgnoreCase("ORDER-SERVICE")){
					urlForCreateOrders = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort()+"/api/orders"+ "/" + "create-order";
				}
			});
		});
		
		
		
//		urlForCreateOrders=baseUrlForOrders+ "/" + "create-order";
//		 Flux accounts= webClientBuilder.build().get().uri("http://accountservice/customer/{customer}", id).retrieve().bodyToFlux(Account.class);
		Mono<String> bodyToMono = webClient.get().uri(urlForCreateOrders).retrieve()
        .bodyToMono(String.class);
		logger.info("end create customer {}",bodyToMono.block());
		return "customer created succssfully";
	}
	
	@GetMapping("/get-customer")
	public String getCustomer() {
		return customerService.getCustomer();
	}

}
