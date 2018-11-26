package br.com.neolog.models;

import java.util.LinkedList;
import java.util.List;

public class PresentationSolution
{
    private List<SeparatedItem> items = new LinkedList<>();
    private long totalValue;

    public PresentationSolution()
    {
    }

    private PresentationSolution(
        final List<SeparatedItem> items,
        final long totalValue )
    {

        this.items = items;
        this.totalValue = totalValue;

    }

    public long getTotalValue()
    {
        return totalValue;
    }

    public List<SeparatedItem> getItems()
    {
        return items;
    }

    public static PresentationSolution of(
        final List<SeparatedItem> items,
        final long total )
    {
        return new PresentationSolution( items, total );
    }

}
