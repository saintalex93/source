package br.com.neolog.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "Category" )
public class Category
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "category_sequence" )
    @SequenceGenerator( name = "category_sequence", sequenceName = "category_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;
    @Column( unique = true )
    private String code;
    private String name;

    public Category()
    {

    }

    public static Category create(
        final String code,
        final String name )
    {

        return new Category( code, name );

    }

    private Category(
        final String code,
        final String name )
    {
        super();
        this.code = code;
        this.name = name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(
        final Integer id )
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
        return name;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    @Override
    public boolean equals(
        final Object o )
    {
        if( this == o ) {
            return true;
        }
        if( ! ( o instanceof Category ) ) {
            return false;
        }
        final Category c = (Category) o;
        if( this.name.equals( c.name ) ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( name );
    }

    @Override
    public String toString()
    {
        return this.name;
    }

}
