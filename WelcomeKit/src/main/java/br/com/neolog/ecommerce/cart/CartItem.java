package br.com.neolog.ecommerce.cart;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

import br.com.neolog.ecommerce.product.Product;

@Entity
@Table( name = "cart_item" )
public class CartItem
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @NotNull
    private int id;
    @NotNull
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "product", foreignKey = @ForeignKey( name = "fk_cartItem_product" ) )
    private Product product;
    @Min( 1 )
    private int quantity;
    @NotNull
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "cart", foreignKey = @ForeignKey( name = "fk_cartItem_cart" ) )
    private Cart cart;

    public CartItem()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(
        final int id )
    {
        this.id = id;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(
        final Product product )
    {
        this.product = product;
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

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(
        final Cart cart )
    {
        this.cart = cart;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode( getId() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {

        if( this == obj ) {
            return true;
        }
        if( obj instanceof CartItem ) {
            return true;
        }
        final CartItem cartItem = (CartItem) obj;
        return Objects.equal( this.getId(), cartItem.getId() ) && Objects.equal( this.getCart(), cartItem.getCart() );

    }

}
