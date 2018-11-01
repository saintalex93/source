package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class OptimizationInvalidValueException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 4586439470103170339L;

    public OptimizationInvalidValueException()
    {
        super( "O valor a ser otimizado deve ser maior do que R$ 0,00" );
    }

}
