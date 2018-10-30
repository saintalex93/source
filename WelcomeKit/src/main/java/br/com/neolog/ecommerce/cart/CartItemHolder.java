package br.com.neolog.ecommerce.cart;

import javax.validation.constraints.Min;

import com.google.common.base.MoreObjects;

public class CartItemHolder
{

    private int productId;
    @Min( 1 )
    private int quantity;

    public CartItemHolder()
    {
    }

    public int getProductId()
    {
        return productId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "Product Id", getProductId() ).add( "Quantity", getQuantity() ).toString();
    }

}
