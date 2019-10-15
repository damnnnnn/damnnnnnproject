package com.lixin.bootjwtserver.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UserAopConfig  extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
		System.out.println("UserAopConfig  is  addInterceptors  start...");
		registry.addInterceptor(getUserAop()).addPathPatterns("/**").excludePathPatterns("/users/login");
	}
	
	@Bean
	public UserAop  getUserAop()
	{
		return new UserAop();
	}

}
