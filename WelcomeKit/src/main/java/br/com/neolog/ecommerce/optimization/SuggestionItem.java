package br.com.neolog.ecommerce.optimization;

import java.util.Objects;

public class SuggestionItem
{
    private int productCode;
    private String name;
    private long quantity;
    private long value;

    public SuggestionItem()
    {
    }

    public static SuggestionItem create(
        final int productCode,
        final String name,
        final long quantity,
        final long value )
    {
        final SuggestionItem suggestionItem = new SuggestionItem();
        suggestionItem.productCode = Objects.requireNonNull( productCode );
        suggestionItem.name = Objects.requireNonNull( name );
        suggestionItem.quantity = quantity;
        suggestionItem.value = value;
        return suggestionItem;
    }

    public int getProductCode()
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
