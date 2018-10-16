package br.com.neolog.ecommerce.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter( urlPatterns = {
    "/product/*",
    "/category/*",
    "/customer/*",
    "/stock/*",
    "/cart/*",
    "/session/*"
} )
public class ServletFIlter
    implements
        Filter
{

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

    }

    public void destroy()
    {

    }

}
