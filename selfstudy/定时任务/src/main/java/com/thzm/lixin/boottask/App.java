package com.thzm.lixin.boottask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//springboot 通过全程注解引导项目

//为什么要注解？简化传统的开发流程，提高开发效率


//1.springboot  框架   

//struts1.2--struts2.0 ---springmvc

@SpringBootApplication
@EnableScheduling 
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
