package br.com.neolog.ecommerce.optimization.problem;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Problem
{
    private long target;
    private List<Item> problemItem;

    public Problem()
    {
    }

    public static Problem create(
        final long target,
        final List<Item> problemItem )
    {
        return new Problem( target, problemItem );
    }

    private Problem(
        final long target,
        final List<Item> problemItem )
    {
        Preconditions.checkArgument( target >= 0 );
        this.target = target;
        this.problemItem = ImmutableList.copyOf( Objects.requireNonNull( problemItem ) );
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
