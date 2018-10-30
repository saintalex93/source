package br.com.neolog.ecommerce.cart;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_sequence" )
    @SequenceGenerator( name = "cart_sequence", sequenceName = "cart_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;
    @Min( 0 )
    private long totalValue;
    @NotNull
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "customer", foreignKey = @ForeignKey( name = "fk_cart_user" ) )
    private Customer customer;
    @NotNull
    @Enumerated( EnumType.STRING )
    private CartStatus status;

    public Cart(
        final long totalValue,
        final Customer customer,
        final CartStatus status )
    {
        this.totalValue = totalValue;
        this.customer = customer;
        this.status = status;
    }

    public Cart()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public Double getTotalValue()
    {
        return this.totalValue / 100.0;
    }

    public void setTotalValue(
        final Double totalValue )
    {
        this.totalValue = Math.round( totalValue * 10 * 10 );
    }

    public Customer getCustomer()
    {
        return customer;
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
        if( ! ( obj instanceof Cart ) ) {
            return false;
        }
        final Cart cart = (Cart) obj;
        return Objects.equal( getId(), cart.getId() ) &&
            Objects.equal( getTotalValue(), cart.getTotalValue() ) &&
            Objects.equal( getCustomer(), cart.getCustomer() ) &&
            Objects.equal( getStatus(), cart.getStatus() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "ID", id )
            .add( "Total Cart", totalValue )
            .add( "User", customer )
            .add( "Status", status )
            .toString();
    }

}
