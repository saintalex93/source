package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.FORBIDDEN )
public class TokenNotFoundException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 5293478373430272536L;

    public TokenNotFoundException()
    {
        super( "O Token não foi passado na requisição" );
    }

}
