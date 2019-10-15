package com.lixin.usereurekaclient.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class RestConfig {
	
	
	@Bean
	@LoadBalanced
	public  RestTemplate  getTemplate()
	{
		return  new RestTemplate();
	}
	

}
