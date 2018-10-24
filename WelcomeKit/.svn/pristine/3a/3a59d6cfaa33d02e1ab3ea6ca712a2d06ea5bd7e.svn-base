package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CartCustomerException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 5426866135870809631L;

    public CartCustomerException()
    {
        super( "Você não é dono deste carrinho" );
    }
}
