package br.com.neolog.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.neolog.exceptions.EmailAlreadyInUseException;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.pojo.User;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public void addUser(User user) throws EmailAlreadyInUseException {
		if (user == null) {
			throw new NullPointerException();
		}

		if (user.getPassword() == null) {
			throw new IllegalArgumentException(
					"Usuário deve ter um Password válido!!!");
		}

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyInUseException(
					"O email informado, já encontra-se em uso!!!");
		}
		Pattern p = Pattern
				.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher m = p.matcher(user.getEmail());
		if (m.matches() == false) {
			throw new IllegalArgumentException("Digite um email válido!!!");
		}
		ShaPasswordEncoder pass = new ShaPasswordEncoder();
		user.setPassword(pass.encodePassword(user.getPassword(), null));
		userRepository.save(user);
	}

	public void removeUser(String email) throws UserNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("Usuário não encontrado!!!");
		}

		userRepository.delete(user);
	}

	public User find(String email) throws UserNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("Usuário não encontrado!!!");
		}
		return user;
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
}