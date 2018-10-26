package br.com.neolog.ecommerce.authentication;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.neolog.ecommerce.CustomerThreadLocal;

@WebFilter( urlPatterns = {
    "/product/*",
    "/category/*",
    "/customer/search",
    "/customer/search/{id}",
    "/customer/update",
    "/customer/inactive",
    "/stock/*",
    "/cart/*"
} )
public class AuthenticationTokenFilter
    implements
        Filter
{

    private static final Logger logger = Logger.getLogger( AuthenticationTokenFilter.class.getName() );

    private final SessionService sessionService;

    public AuthenticationTokenFilter(
        final SessionService sessionService )
    {
        this.sessionService = sessionService;
    }

    @Override
    public void init(
        final FilterConfig filterConfig )
        throws ServletException
    {
        logger.info( "Filtro criado" );
    }

    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        if( httpRequest.getHeader( "token" ) == null ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, "Token não encontrado" );
            return;

        }
        final Session session = sessionService.verifyToken( httpRequest.getHeader( "token" ) );
        if( session == null ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, "Token Inválido" );
            return;
        }
        if( session.getCustomer().getInactive() == true ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, "Cliente Inativo" );
            return;
        }

        if( session.getExpirationDate().isBeforeNow() ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, "Tempo de sessão Esgotado. Refaça o login" );
            return;
        }

        final long tempoInicial = System.currentTimeMillis();
        CustomerThreadLocal.set( session.getCustomer().getId() );
        try {
            chain.doFilter( request, response );
        } finally {
            CustomerThreadLocal.remove();
        }

        final long tempoFinal = System.currentTimeMillis();
        final String uri = ( (HttpServletRequest) request ).getRequestURI();
        final String separation = "\n----------------------------------------------------------------------------------\n";
        System.out.println( separation + "Tempo da requisicao de da URI " + uri
            + " Demorou " + ( tempoFinal - tempoInicial ) + " ms" + separation );
    }

    @Override
    public void destroy()
    {
        logger.warning( "Filtro finalizado" );
    }

}
