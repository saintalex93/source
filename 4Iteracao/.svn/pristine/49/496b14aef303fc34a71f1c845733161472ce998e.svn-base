package br.com.neolog.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.pojo.Session;
import br.com.neolog.repository.SessionRepository;

@Component
public class TokenService {
	@Autowired
	private SessionRepository sessionRepository;

	public boolean validateToken(String token) {

		Session session = sessionRepository.findByToken(token);
		if (session != null
				&& Calendar.getInstance().before(session.getExpirationDate())) {
			return true;

		}
		return false;

	}

}
