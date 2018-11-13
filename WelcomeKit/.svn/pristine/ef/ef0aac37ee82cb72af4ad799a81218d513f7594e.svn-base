package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class ProblemNullException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 8001603029075441234L;

    public ProblemNullException()
    {
        super( "O parâmetro (problem) informado está nulo" );
    }

}
