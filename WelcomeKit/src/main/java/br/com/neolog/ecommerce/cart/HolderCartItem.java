package br.com.neolog.ecommerce.cart;

import javax.validation.constraints.Min;

import com.google.common.base.MoreObjects;

public class HolderCartItem
{

    private int productId;
    @Min( 1 )
    private int quantity;

    public HolderCartItem()
    {
    }

    public HolderCartItem(
        final int idProduct,
        final int quantity )
    {
        super();
        this.productId = idProduct;
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
