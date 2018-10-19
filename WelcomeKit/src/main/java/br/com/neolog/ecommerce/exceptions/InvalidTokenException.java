package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.FORBIDDEN )
public class InvalidTokenException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 1840251167551112741L;

    public InvalidTokenException()
    {
        super( "O token est� incorreto" );
    }

}
