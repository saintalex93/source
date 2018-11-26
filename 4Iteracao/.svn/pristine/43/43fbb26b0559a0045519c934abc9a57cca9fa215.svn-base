package br.com.neolog.models;

public class OptimizationHolder
{

    private long target;
    private ProblemType type;

    public OptimizationHolder()
    {
    }

    public static OptimizationHolder newInstance(
        final long target,
        final ProblemType type )
    {
        return new OptimizationHolder( target, type );
    }

    private OptimizationHolder(
        final long target,
        final ProblemType type )
    {
        this.target = target;
        this.type = type;
    }

    public long getTarget()
    {
        return target;
    }

    public ProblemType getType()
    {
        return type;
    }

}
