package br.com.neolog.ecommerce.optimization.solution;

public class SolutionItem
{
    private Integer productCode;
    private String name;
    private long quantity;
    private long value;

    public SolutionItem()
    {
    }

    public SolutionItem(
        final Integer productCode,
        final String name,
        final long quantity,
        final long value )
    {
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    public Integer getProductCode()
    {
        return productCode;
    }

    public String getName()
    {
        return name;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public double getValue()
    {
        return value / 100.0;
    }

}
