package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class CartItemNotFoundException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 271866497633590446L;

    public CartItemNotFoundException()
    {
        super( "O item não está no carrinho" );
    }

}
