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

    public static CartItemHolder create(
        final int productId,
        @Min( 1 ) final int quantity )
    {
        return new CartItemHolder( productId, quantity );
    }

    private CartItemHolder(
        final int productId,
        @Min( 1 ) final int quantity )
    {
        super();
        this.productId = productId;
        this.quantity = quantity;
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
