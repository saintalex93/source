package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CustomerFillException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 5379080541470454096L;

    public CustomerFillException(
        final String message )
    {
        super( message );
    }
}
