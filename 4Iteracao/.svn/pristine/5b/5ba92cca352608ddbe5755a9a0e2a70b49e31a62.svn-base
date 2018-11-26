package br.com.neolog.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table( name = "product" )
public class Product
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_sequence" )
    @SequenceGenerator( name = "product_sequence", sequenceName = "product_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;
    @Column( unique = true )
    private String code;
    @Column
    private String name;

    @Min( value = 1 )
    private long price;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "category", referencedColumnName = "id" )
    private Category category;
    @Column
    private String description;
    @Column
    private long weight;
    @Column
    private long volume;

    public Product()
    {

    }

    public Product(
        final Integer id,
        final String name,
        final Category category,
        final String description,
        final long weight,
        final long volume )
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.weight = weight;
        this.volume = volume;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(
        final int id )
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(
        final String code )
    {
        this.code = code;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    public long getPrice()
    {
        return this.price;
    }

    public void setPrice(
        final long price )
    {
        this.price = price;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(
        final Category category )
    {
        this.category = category;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(
        final String description )
    {
        this.description = description;
    }

    public long getWeight()
    {
        return weight;
    }

    public void setWeight(
        final long weight )
    {
        this.weight = weight;
    }

    public long getVolume()
    {
        return volume;
    }

    public void setVolume(
        final long volume )
    {
        this.volume = volume;
    }

    @Override
    public boolean equals(
        final Object o )
    {
        if( o == this ) {
            return true;
        }
        if( ! ( o instanceof Product ) ) {
            return false;
        }
        final Product p = (Product) o;

        if( this.code.equals( p.code ) ) {
            if( this.name.equals( p.getName() ) ) {
                if( this.category.equals( p.category ) ) {
                    if( this.description.equals( p.description ) ) {
                        if( this.weight == p.weight ) {
                            if( this.volume == p.volume ) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString()
    {

        return "Flame: " + this.name;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( name, description );
    }
}
