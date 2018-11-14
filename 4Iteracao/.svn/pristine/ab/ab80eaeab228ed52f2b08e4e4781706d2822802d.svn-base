package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.EmailAlreadyInUseException;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.pojo.User;
import br.com.neolog.services.UserService;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@RequestBody User user)
			throws EmailAlreadyInUseException {
		userService.addUser(user);
		return "Usuário adicionado com sucesso!";
	}

	@RequestMapping(value = "/user/remove", method = RequestMethod.DELETE)
	public String removeUser(@RequestBody String email)
			throws UserNotFoundException {
		userService.removeUser(email);
		return "Usuário removido com sucesso!";
	}

	@RequestMapping(value = "user/find", method = RequestMethod.POST)
	public String find(@RequestBody String email) throws UserNotFoundException {
		User user = userService.find(email);
		String result = "NAME: " + user.getName() + " | EMAIL: "
				+ user.getEmail();
		return result;
	}

	@RequestMapping(value = "user/all", method = RequestMethod.GET)
	public String findAll() {
		String result = "USUÁRIOS CADASTRADOS" + "\n";
		for (User u : userService.findAll()) {
			result = result.concat("NAME: " + u.getName() + " | EMAIL: "
					+ u.getEmail() + "\n");
		}
		return result;
	}

}
