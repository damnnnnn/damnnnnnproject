package com.thzm.lixin.bootvueproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan("com.thzm.lixin.bootvueproject.filter")
public class App 
{
    public static void main( String[] args )
    {
       SpringApplication.run(App.class, args);
    }
}
