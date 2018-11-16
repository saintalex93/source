package br.com.neolog.ecommerce.optimization;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.ProvideToken;
import br.com.neolog.ecommerce.optimization.suggestion.Suggestion;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OptimizationIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnFullStock()
    {
        final Suggestion suggest = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "suggest-cart/150021.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Suggestion.class );
        assertThat( suggest ).isNotNull();
    }

    @Test
    public void shouldReturnEmptyListWhenTargetLesserThanMinProductValue()
    {
        final Suggestion suggest = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "suggest-cart/2" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Suggestion.class );
        assertThat( suggest.getSuggestionItem() ).isEmpty();
    }

    @Test
    public void shouldReturnAproximatedValueWhenTargetIs1500()
    {
        final Suggestion suggest = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "suggest-cart/1500.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Suggestion.class );
        assertThat( suggest.getResult() * 100 ).isEqualByComparingTo( 1420d );
    }

    @Test
    public void shouldReturnExactValueWhenTargetIs1420()
    {
        final Suggestion suggest = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "suggest-cart/1420.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Suggestion.class );
        assertThat( suggest.getResult() * 100 ).isEqualByComparingTo( 1420d );
    }
}
