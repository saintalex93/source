package br.com.neolog.ecommerce.optimization.problem;

import java.util.List;
import java.util.Objects;

public class Problem
{
    private final long target;
    private final List<Item> problemItem;

    public Problem(
        final long target,
        final List<Item> problemItem )
    {
        this.target = target;
        this.problemItem = Objects.requireNonNull( problemItem );
    }

    public long getTarget()
    {
        return target;
    }

    public List<Item> getProblemItems()
    {
        return problemItem;
    }

}
