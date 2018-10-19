package br.com.neolog.ecommerce.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.CustomerThreadLocal;

@WebFilter( filterName = "ApplicationFilter", urlPatterns = {
    "/product/*",
    "/category/*",
    "/customer/*",
    "/stock/*",
    "/cart/*",

},
    initParams = {
        @WebInitParam( name = "/session", value = "/*" )
    } )
@Component
public class ServletFIlter
    implements
        Filter
{

    @Autowired
    private SessionService sessionService;

    public void init(
        final FilterConfig filterConfig )
        throws ServletException
    {

    }

    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

        // Está vazando para a a url session
        // if( request.getParameter( "token" ) == null ) {
        // throw new IllegalAccessError();
        // }
        // final Session session = sessionService.verifyToken(
        // request.getParameter( "token" ) );
        final Session session = sessionService.verifyToken( ( (HttpServletRequest) request ).getHeader( "token" ) );

        final long tempoInicial = System.currentTimeMillis();
        CustomerThreadLocal.set( session.getCustomer() );
        CustomerThreadLocal.get();

        chain.doFilter( request, response );

        final long tempoFinal = System.currentTimeMillis();

        final String uri = ( (HttpServletRequest) request ).getRequestURI();

        final String separation = "\n----------------------------------------------------------------------------------\n";

        System.out.println( separation + "Tempo da requisicao de da URI " + uri
            + " Demorou " + ( tempoFinal - tempoInicial ) + " ms" + separation );

    }

    public void destroy()
    {

    }

}
