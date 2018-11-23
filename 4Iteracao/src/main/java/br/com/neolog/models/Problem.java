package br.com.neolog.models;

import java.util.Set;

public class Problem
{

    private Set<HolderCodeValue> products;
    private double target;

    public Problem()
    {
    }

    public static Problem create(
        final Set<HolderCodeValue> products,
        final double target )
    {
        return new Problem( products, target );
    }

    private Problem(
        final Set<HolderCodeValue> products,
        final double target )
    {
        super();
        this.products = products;
        this.target = target;
    }

    public Set<HolderCodeValue> getProducts()
    {
        return products;
    }

    public void setProducts(
        final Set<HolderCodeValue> products )
    {
        this.products = products;
    }

    public double getTarget()
    {
        return target;
    }

    public void setTarget(
        final double target )
    {
        this.target = target;
    }

}
