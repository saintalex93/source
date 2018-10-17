package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CustomerDuplicatedEmailException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 4154414949064067475L;

    public CustomerDuplicatedEmailException()
    {
        super( "O email informado já existe" );
    }

}
