package br.com.neolog.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table( name = "cart" )
public class Cart
{
    public enum Status
    {
        COMPLETED,
        NOTCOMPLETED,
        CANCELED
    }

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_sequence" )
    @SequenceGenerator( name = "cart_sequence", sequenceName = "cart_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Min( 0 )
    private long totalValue;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn( name = "cart_id" )
    private Set<OrderItem> orderItens = new HashSet<OrderItem>();
    private String status;

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id", referencedColumnName = "id" )
    private Customer user;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(
        final Integer id )
    {
        this.id = id;
    }

    public Set<OrderItem> getOrderItens()
    {
        return this.orderItens;
    }

    public void setOrderItens(
        final Set<OrderItem> newOrderItens )
    {
        this.orderItens = newOrderItens;
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

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(
        final Status status )
    {
        this.status = status.toString();
    }

}
