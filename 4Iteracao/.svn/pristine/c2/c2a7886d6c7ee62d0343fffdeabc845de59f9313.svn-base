package br.com.neolog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Tratamento para quando {@link Controller}s lançarem exceções.<br>
 * Mais informações em <a
 * href="http://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc"
 * >Exception handling in Spring MVC</a>.
 * <p>
 * Obs.: {@link RestController}s também são {@link Controller}s.
 */
@ControllerAdvice
public class ControllersExceptionHandler {
	/**
	 * Tratamento genérico para qualquer exceção {@link Exception}. <br>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	static ModelAndView HandleException(final Exception e) {
		return new ModelAndView(new MappingJackson2JsonView(), "error",
				e.getMessage());
	}
}
