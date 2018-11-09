package br.com.neolog.ecommerce.optimization.problem;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public class Item
{
    private final int productCode;
    private final long value;
    private long quantity;

    public Item(
        final int productCode,
        final long value,
        final long quantity )
    {

        this.productCode = productCode;
        this.value = value;
        this.quantity = quantity;

    }

    public int getProductCode()
    {
        return productCode;
    }

    public long getValue()
    {
        return value;
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

    @Override
    public String toString()
    {

        return MoreObjects.toStringHelper( this ).add( "Product Code", productCode ).add( "Value", value )
            .add( "Quantity", quantity ).toString();

    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }
        if( ! ( obj instanceof Item ) ) {
            return false;
        }
        final Item other = (Item) obj;
        return Objects.equals( getProductCode(), other.getProductCode() ) && Objects.equals( getValue(), other.getValue() );
    }

}
