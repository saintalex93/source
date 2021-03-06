package br.com.neolog.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.neolog.filter.ApplicationFilter;

@SpringBootApplication
@ComponentScan( basePackages = "br.com.neolog" )
@EnableJpaRepositories( "br.com.neolog.repository" )
@EntityScan( basePackages = "br.com.neolog" )
public class Application
    extends
        SpringApplication
{

    @Autowired
    private ApplicationFilter mainFilter;

    @Bean
    public FilterRegistrationBean<ApplicationFilter> filterRegistrationBean()
    {

        final FilterRegistrationBean<ApplicationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter( mainFilter );

        registrationBean.addUrlPatterns( "/cart/*" );
        registrationBean.addUrlPatterns( "/category/*" );
        registrationBean.addUrlPatterns( "/user/remove" );
        registrationBean.addUrlPatterns( "/user/find" );
        registrationBean.addUrlPatterns( "/user/all" );
        registrationBean.addUrlPatterns( "/product/*" );
        registrationBean.addUrlPatterns( "/optimization/*" );
        registrationBean.addUrlPatterns( "/stock/*" );
        registrationBean.addUrlPatterns( "/session/logout" );
        registrationBean.addUrlPatterns( "/user/*" );

        return registrationBean;
    }

    public static void main(
        final String[] args )
    {

        SpringApplication.run( Application.class, args );

    }
}
