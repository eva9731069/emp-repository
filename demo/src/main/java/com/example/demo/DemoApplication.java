package com.example.demo;

//import filter.errorFilter;

import com.emailService.MailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com/controller","com/service","com.aop.service","com.config","com.timerTask","com.emailService","com.kafka"})
@MapperScan("com.mapper")
@EnableScheduling
public class DemoApplication {

	@Autowired
	private MailService sendMailservice;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);        
	}

//	@Bean
//	public FilterRegistrationBean<errorFilter> yourFilter() {
//		FilterRegistrationBean<errorFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setFilter(new errorFilter());
//		registrationBean.addUrlPatterns("/*"); // 設置Filter攔截的URL模式，此例中攔截所有URL
//		return registrationBean;
//	}

}
