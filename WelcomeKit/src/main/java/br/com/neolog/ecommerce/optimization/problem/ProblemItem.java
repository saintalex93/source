package br.com.neolog.ecommerce.optimization.problem;

import com.google.common.base.MoreObjects;

public class ProblemItem
{
    private int productCode;
    private long value;
    private long quantity;

    public ProblemItem()
    {
    }

    public ProblemItem(
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

        return MoreObjects.toStringHelper( this )
            .add( "Product Code", productCode )
            .add( "Value", value )
            .add( "Quantity", quantity )
            .toString();

    }

}
