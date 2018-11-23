package br.com.neolog.models;

import java.util.Collections;
import java.util.Set;

public class Solution
{
    private Set<HolderCodeValue> products;
    private final long totalvalue;

    public static Solution create(
        final Set<HolderCodeValue> products )
    {

        return new Solution( products );
    }

    public static Solution emptySolution()
    {
        return new Solution( Collections.emptySet() );
    }

    public long getTotalvalue()
    {
        return totalvalue;
    }

    private Solution(
        final Set<HolderCodeValue> products )
    {
        this.products = products;
        this.totalvalue = products.stream().mapToLong( HolderCodeValue::getValue ).sum();

    }

    public Set<HolderCodeValue> getProducts()
    {
        return products;
    }

    public void setProducts(
        final Set<HolderCodeValue> codes )
    {
        this.products = codes;
    }

}
