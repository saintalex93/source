package br.com.neolog.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.pojo.Session;
import br.com.neolog.pojo.User;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.UserRepository;

/**
 * Classe responsável por fornecer serviços de autenticação do {@link User},
 * criar {@link Session}, fazer login e logout.
 * 
 * @author igor.kurman
 * 
 */
@Component
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	TokenService tokenService;

	/**
	 * Este método criar uma {@link Session} para o {@link User} corrente.
	 * 
	 * @param user
	 *            é o {@link User} corrente ao qual a session deve ser
	 *            atribuída.
	 */
	private void createSession(User user) {
		Session session = new Session();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		session.setUser(user);

		session.setLoginDate(calendar);

		calendar2.add(Calendar.DATE, 1);

		session.setExpirationDate(calendar2);
		session.setToken("" + calendar.getTimeInMillis());

		sessionRepository.save(session);
	}

	/**
	 * Método que verifica se o {@code email} informado pertence a algum
	 * {@link User} existente no sistema e se o {@code password} informado é
	 * válido.
	 * 
	 * @param email
	 *            que o método usa para procurar o {@link User} no sistema.
	 * @param password
	 *            para verificar a autenticidade do {@link User}.
	 * @return NÃO DEFINIDO
	 * @throws UserNotFoundException
	 *             se o {@code email} informado não retornar nenhum usuário da
	 *             base de dados.
	 */
	public String login(String email, String password)
			throws UserNotFoundException {
		User user = userRepository.findByEmail(email);

		ShaPasswordEncoder encoder = new ShaPasswordEncoder();

		if (user == null) {
			throw new UserNotFoundException("Usuário não existe no sistema!!!");
		}

		if (encoder.isPasswordValid(user.getPassword(), password, null)) {

			if (sessionRepository.findByUserEmail(email) == null) {
				createSession(user);
			} else {
				Session session = sessionRepository.findByUserEmail(email);

				sessionRepository.delete(session.getId());
				createSession(user);
			}

		}
		return sessionRepository.findByUserEmail(email).getToken();
	}

	/**
	 * Termina a sessão do {@link User} corrente.
	 * 
	 * @param token
	 *            para validar a operação.
	 * @return
	 * @throws Exception
	 */
	public boolean logout(String token) throws Exception {

		if (tokenService.validateToken(token)) {

			CurrentUserHolder.setUser(null);
		} else {
			throw new Exception("Token inv�lido");
		}

		return true;
	}
}