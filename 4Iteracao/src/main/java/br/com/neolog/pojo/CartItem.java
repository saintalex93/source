package br.com.neolog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "cart_item" )
public class CartItem
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_item_sequence" )
    @SequenceGenerator( name = "cart_item_sequence", sequenceName = "cart_item_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "product", foreignKey = @ForeignKey( name = "fk_cart_item_product" ) )
    private Product product;

    @Column
    private int quantity;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "cart", foreignKey = @ForeignKey( name = "fk_cart_item_cart" ) )
    private Cart cart;

    public CartItem()
    {

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(
        final Integer id )
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( cart == null ? 0 : cart.hashCode() );
        result = prime * result + ( product == null ? 0 : product.hashCode() );
        return result;
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }
        if( obj == null ) {
            return false;
        }
        if( ! ( obj instanceof CartItem ) ) {
            return false;
        }
        final CartItem other = (CartItem) obj;
        if( cart == null ) {
            if( other.cart != null ) {
                return false;
            }
        } else if( ! cart.equals( other.cart ) ) {
            return false;
        }
        if( product == null ) {
            if( other.product != null ) {
                return false;
            }
        } else if( ! product.equals( other.product ) ) {
            return false;
        }
        return true;
    }

    public void setCart(
        final Cart cart )
    {
        this.cart = cart;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "PRODUCT", product )
            .add( "QUANTITY", quantity ).toString();
    }

}
