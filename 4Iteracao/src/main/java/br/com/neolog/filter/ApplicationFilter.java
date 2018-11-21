package br.com.neolog.filter;

import java.io.IOException;
import java.util.logging.Logger;

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
public class ApplicationFilter
    extends
        GenericFilterBean
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SessionRepository sessionRepository;

    Logger logger = Logger.getLogger( ApplicationFilter.class.getName() );

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

        final String token = req.getHeader( "token" );

        if( token == null ) {
            res.sendError( HttpServletResponse.SC_NOT_FOUND, "Token não inserido" );
            return;
        }

        if( tokenService.validateToken( token ) == true ) {
            CurrentUserHolder.setUser( sessionRepository.findByToken(
                token ).getCustomer() );
            logger.info( "USUÁRIO ATUAL: "
                + CurrentUserHolder.getUser().getName() );
            chain.doFilter( req, response );

        } else {
            res.sendError( HttpServletResponse.SC_FORBIDDEN, "Token Inválido" );
        }

    }
}
