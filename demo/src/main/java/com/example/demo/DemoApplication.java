package com.example.demo;

//import filter.errorFilter;
import com.emailService.MailService;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com/controller","com/service","com.aop.service","com.config","com.timerTask","com.emailService"})
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
