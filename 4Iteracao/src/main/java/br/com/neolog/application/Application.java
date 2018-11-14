package br.com.neolog.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.neolog.filter.MainFilter;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.neolog")
@EnableJpaRepositories("br.com.neolog.repository")
@EntityScan(basePackages = "br.com.neolog")
public class Application extends SpringApplication {

	@Autowired
	private MainFilter mainFilter;

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(mainFilter);

		registrationBean.addUrlPatterns("/cart/*");
		registrationBean.addUrlPatterns("/stock/*");
		registrationBean.addUrlPatterns("/user/*");
		// registrationBean.addUrlPatterns("/category/*");

		return registrationBean;
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}
}
