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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import br.com.neolog.ecommerce.customer.Customer;

@Entity
@Table( name = "cart" )
public class Cart
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @NotNull
    private int id;
    @Min( 1 )
    private long totalValue;
    @NotNull
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user", foreignKey = @ForeignKey( name = "fk_cart_user" ) )
    private Customer user;
    @NotNull
    private CartStatus status;

    public Cart()
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

    public long getTotalValue()
    {
        return totalValue;
    }

    public void setTotalValue(
        final long totalValue )
    {
        this.totalValue = totalValue;
    }

    public Customer getUser()
    {
        return user;
    }

    public void setUser(
        final Customer user )
    {
        this.user = user;
    }

    public CartStatus getStatus()
    {
        return status;
    }

    public void setStatus(
        final CartStatus status )
    {
        this.status = status;
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
        if( obj instanceof Cart ) {
            return true;
        }
        final Cart cart = (Cart) obj;
        return Objects.equal( this.getId(), cart.getId() ) && Objects.equal( this.getUser(), cart.getUser() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "ID", id )
            .add( "Total Cart", totalValue )
            .add( "User", user )
            .add( "Status", status )
            .toString();
    }

}
