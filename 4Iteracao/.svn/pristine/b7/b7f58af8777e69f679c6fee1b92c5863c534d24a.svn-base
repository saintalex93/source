package br.com.neolog.models;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public class SeparatedItem
{

    private String code;
    private String name;
    private long quantity;
    private long value;

    public SeparatedItem()
    {
    }

    public static SeparatedItem newInstance(
        final String code,
        final String name,
        final long quantity,
        final long value )
    {

        return new SeparatedItem( code, name, quantity, value );
    }

    private SeparatedItem(
        final String code,
        final String name,
        final long quantity,
        final long value )
    {
        super();
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
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

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(
        final long quantity )
    {
        this.quantity = quantity;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(
        final long value )
    {
        this.value = value;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash( code, name );
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( ! ( obj instanceof SeparatedItem ) ) {
            return false;
        }
        final SeparatedItem object = (SeparatedItem) obj;
        return Objects.equals( getCode(), object.getCode() ) && Objects.equals( getName(), object.getName() );
    }

    @Override
    public String toString()
    {

        return MoreObjects.toStringHelper( this.getClass() )
            .add( "CODE", code )
            .add( "NAME", name )
            .add( "QUANTITY", quantity )
            .add( "VALUE", value )
            .toString();
    }

}
