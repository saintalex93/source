package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CartItemStockException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 4609844768933644604L;

    public CartItemStockException(
        final String product )
    {
        super( "Estoque do produto " + product + " insuficiente para compra" );
    }
}
