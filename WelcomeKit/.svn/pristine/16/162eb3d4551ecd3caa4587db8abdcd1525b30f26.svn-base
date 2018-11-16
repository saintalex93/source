package br.com.neolog.ecommerce.optimization.suggestion;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;

public class Suggestion
{
    private long result;
    List<SuggestionItem> suggestionItem;

    public Suggestion()
    {
    }

    public static Suggestion create(
        final long result,
        final List<SuggestionItem> suggestionItem )
    {
        final Suggestion suggestion = new Suggestion();
        suggestion.result = result;
        suggestion.suggestionItem = Objects.requireNonNull( suggestionItem );
        return suggestion;
    }

    public double getResult()
    {
        return result / 100.0;
    }

    public List<SuggestionItem> getSuggestionItem()
    {
        return suggestionItem;
    }

    public static Suggestion empty()
    {
        return create( 0, ImmutableList.of() );

    }
}
