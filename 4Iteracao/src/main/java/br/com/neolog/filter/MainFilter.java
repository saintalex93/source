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
 * Classe responsÃ¡vel por filtrar as requisiÃ§Ãµes, verificar a validade do
 * token e setar o usuÃ¡rio corrente com o auxÃ­lio da classe
 * {@link CurrentUserHolder}.
 *
 * @author igor.kurman
 */
@Component
@WebFilter
public class MainFilter
    extends
        GenericFilterBean
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SessionRepository sessionRepository;

    /**
     *
     */
    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String token = req.getParameter( "token" );

        if( token == null ) {
            res.setStatus( HttpServletResponse.SC_FORBIDDEN );
        } else {

            if( tokenService.validateToken( token ) == true ) {
                try {
                    CurrentUserHolder.setUser( sessionRepository.findByToken(
                        token ).getCustomer() );
                    System.err.println( "USUÃ�RIO ATUAL: "
                        + CurrentUserHolder.getUser().getName() );
                    chain.doFilter( req, response );
                } finally {
                    CurrentUserHolder.remove();
                }

            } else {
                res.setStatus( HttpServletResponse.SC_FORBIDDEN );
            }

        }
    }
}
