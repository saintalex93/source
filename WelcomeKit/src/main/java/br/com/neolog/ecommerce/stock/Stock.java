package br.com.neolog.ecommerce.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

import br.com.neolog.ecommerce.product.Product;

@Entity
@Table( name = "stock" )

public class Stock
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @NotNull
    @Column( nullable = false )
    private int quantity;

    @NotNull
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "product", foreignKey = @ForeignKey( name = "fk_stock_product" ) )
    private Product product;

    public Stock()
    {
    }

    public Stock(
        final int quantity,
        final Product stock )
    {
        this.quantity = quantity;
        this.product = stock;
    }

    public void setQuantity(
        final int quantity )
    {
        this.quantity = quantity;
    }

    public Stock(
        final Integer id,
        final int quantity,
        final Product stock )
    {
        this.id = id;
        this.quantity = quantity;
        this.product = stock;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( id == null ? 0 : id.hashCode() );
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

        if( ! ( obj instanceof Stock ) ) {
            return false;
        }
        final Stock other = (Stock) obj;

        return Objects.equal( this.getId(), other.getId() ) && Objects.equal( this.getId(), other.getId() );
    }

    public Integer getId()
    {
        return id;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Product getProduct()
    {
        return product;
    }

}
