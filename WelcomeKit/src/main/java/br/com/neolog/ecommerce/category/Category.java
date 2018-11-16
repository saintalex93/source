package br.com.neolog.ecommerce.category;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "category" )
public class Category
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "category_sequence" )
    @SequenceGenerator( name = "category_sequence", sequenceName = "category_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Column
    private Integer code;

    @Column
    private String name;

    public Category()
    {
    }

    public static Category create(
        final Integer id,
        final Integer code,
        final String name )
    {
        return new Category( id, code, name );
    }

    private Category(
        final Integer id,
        final Integer code,
        final String name )
    {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "Id", id )
            .add( "C�digo", code )
            .add( "Nome", name )
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getId(), getCode() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }

        if( ! ( obj instanceof Category ) ) {
            return false;
        }
        final Category other = (Category) obj;

        return Objects.equals( getId(), other.getId() ) &&
            Objects.equals( getCode(), other.getCode() ) &&
            Objects.equals( getName(), other.getName() );
    }

}
