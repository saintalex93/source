package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.FORBIDDEN )
public class ExpirationSessionException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 2207825287815607406L;

    public ExpirationSessionException()
    {
        super( "Tempo de essão expirado. Efetue o login novamente" );
    }

}
