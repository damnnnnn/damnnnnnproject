package com.thzm.lixin.mavenfreemarkerinterceptor.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UserIntercetorConfig   extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("UserIntercetorConfig  is  addInterceptors  start... ");
		
		//注册一个拦截器，对所有的请求/**对应的业务类进行拦截
		//springboot中可以去new一个对象，但是会脱离springboot的管理，就是不能使用注解。
		//所以最好的应对就是，不new ,而是采用@Bean注册的方法产生对象。
		//registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
		
		registry.addInterceptor(getInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
		super.addInterceptors(registry);
	}
	
	@Bean
	public  UserInterceptor  getInterceptor()
	{
		return  new  UserInterceptor();
	}

}
