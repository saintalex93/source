package br.com.neolog.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
