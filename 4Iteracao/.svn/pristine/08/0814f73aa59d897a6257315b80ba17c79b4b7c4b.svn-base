package br.com.neolog.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "stock" )
public class Stock
{
    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "product", referencedColumnName = "id" )
    Product product;

    Integer quantity;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "stock_sequence" )
    @SequenceGenerator( name = "stock_sequence", sequenceName = "stock_sequence", initialValue = 1, allocationSize = 1 )
    Integer id;

    public Stock()
    {
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

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(
        final Integer quantity )
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        // return this.product.getName() + "Quantidade: ";
        return "" + this.id;
    }

}
