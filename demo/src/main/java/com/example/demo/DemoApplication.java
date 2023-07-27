package com.example.demo;

//import filter.errorFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com/controller","com/service","com.aop.service","com.config"})
@MapperScan("com.mapper")
public class DemoApplication {

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
