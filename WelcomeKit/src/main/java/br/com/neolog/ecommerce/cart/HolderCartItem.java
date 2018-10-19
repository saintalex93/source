package br.com.neolog.ecommerce.cart;

public class HolderCartItem
{

    private int productId;
    private int quantity;

    public HolderCartItem()
    {
    };

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

    public void setProductId(
        final int idProduct )
    {
        this.productId = idProduct;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(
        final int quantity )
    {
        this.quantity = quantity;
    }

}
