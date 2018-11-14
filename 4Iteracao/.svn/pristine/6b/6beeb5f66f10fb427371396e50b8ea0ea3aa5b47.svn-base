package br.com.neolog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.services.TokenService;

/**
 * Classe responsável por filtrar as requisições, verificar a validade do token
 * e setar o usuário corrente com o auxílio da classe {@link CurrentUserHolder}.
 * 
 * @author igor.kurman
 * 
 */
@Component
@WebFilter
public class MainFilter extends GenericFilterBean {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private SessionRepository sessionRepository;

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String token = req.getParameter("token");

		if (token == null) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {

			if (tokenService.validateToken(token) == true) {
				try {
					CurrentUserHolder.setUser(sessionRepository.findByToken(
							token).getUser());
					System.err.println("USUÁRIO ATUAL: "
							+ CurrentUserHolder.getUser().getName());
					chain.doFilter(req, response);
				} finally {
					CurrentUserHolder.remove();
				}

			} else {
				res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}

		}
	}
}
