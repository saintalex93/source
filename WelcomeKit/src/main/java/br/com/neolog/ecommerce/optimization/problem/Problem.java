package br.com.neolog.ecommerce.optimization.problem;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class Problem
{
    private long target;
    private List<Item> problemItem;

    public static Problem create(
        final long target,
        final List<Item> problemItem )
    {
        final Problem problem = new Problem();

        Preconditions.checkArgument( target >= 0 );
        problem.target = target;
        problem.problemItem = ImmutableList.copyOf( Objects.requireNonNull( problemItem ) );
        return problem;
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
