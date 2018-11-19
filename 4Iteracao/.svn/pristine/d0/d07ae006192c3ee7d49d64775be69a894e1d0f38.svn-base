package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.EmailAlreadyInUseException;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.pojo.Customer;
import br.com.neolog.services.AuthenticationService;

@RestController
public class SessionController {
	private AuthenticationService authenticationService;

	@Autowired
	public SessionController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Customer user) throws UserNotFoundException,
			EmailAlreadyInUseException {

		return authenticationService.login(user.getEmail(), user.getPassword());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public boolean logout(@RequestParam String token) throws Exception {

		return authenticationService.logout(token);
	}

}
