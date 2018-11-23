package br.com.neolog.solvers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.AbstractIntegrationTest;
import br.com.neolog.ProvideToken;
import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.ProblemType;
import br.com.neolog.models.SeparatedItem;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OptimizationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnFullStock()
    {

        final SeparatedItem tv = SeparatedItem.newInstance( "1", "TV", 20, 2000 );
        final SeparatedItem batata = SeparatedItem.newInstance( "44556", "BatataPão", 2, 200 );
        final SeparatedItem windao = SeparatedItem.newInstance( "2", "Windão Original", 500, 10000 );

        final List<SeparatedItem> listItems = new ArrayList<>();
        listItems.add( tv );
        listItems.add( batata );
        listItems.add( windao );

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 15002100, ProblemType.VALUE );
        final PresentationSolution presentationSolution = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken
            .provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        Assertions.assertThat( presentationSolution.getItems() ).isEqualTo( listItems );
    }

    @Test
    public void shouldReturnEmptyListWhenTargeValuetLesserThanMinProductValue()
    {

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 2, ProblemType.VALUE );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getItems() ).isEmpty();
    }

    @Test
    public void shouldReturnAproximatedValueWhenTargetIs1500()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 150000, ProblemType.VALUE );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 142000l );
    }

    @Test
    public void shouldReturnExactValueWhenTargetIs1420()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 150000, ProblemType.VALUE );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 142000l );
    }

    @Test
    public void shouldReturnFullStockWhenPassTotalVolumeOfStock()
    {

        final SeparatedItem tv = SeparatedItem.newInstance( "1", "TV", 20, 2000 );
        final SeparatedItem batata = SeparatedItem.newInstance( "44556", "BatataPão", 2, 200 );
        final SeparatedItem windao = SeparatedItem.newInstance( "2", "Windão Original", 500, 10000 );

        final List<SeparatedItem> listItems = new ArrayList<>();
        listItems.add( tv );
        listItems.add( batata );
        listItems.add( windao );

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 5040400, ProblemType.VOLUME );
        final PresentationSolution presentationSolution = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken
            .provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );

        Assertions.assertThat( presentationSolution.getItems() ).isEqualTo( listItems );

    }

    @Test
    public void shouldReturnEmptyListWhenTargeVolumeLesserThanMinProductVolume()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 2, ProblemType.VOLUME );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getItems() ).isEmpty();
    }

    @Test
    public void shouldReturnAproximatedVolumeWhenTargetIs2001()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 2001, ProblemType.VOLUME );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 2000l );
    }

    @Test
    public void shouldReturnExactVolumeWhenTargetIs12000()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 12000, ProblemType.VOLUME );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 12000l );
    }

    @Test
    public void shouldReturnFullStockWhenPassTotalWeightOfStock()
    {
        final SeparatedItem tv = SeparatedItem.newInstance( "1", "TV", 20, 2000 );
        final SeparatedItem batata = SeparatedItem.newInstance( "44556", "BatataPão", 2, 200 );
        final SeparatedItem windao = SeparatedItem.newInstance( "2", "Windão Original", 500, 10000 );

        final List<SeparatedItem> listItems = new ArrayList<>();
        listItems.add( tv );
        listItems.add( batata );
        listItems.add( windao );

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 2530200, ProblemType.WEIGHT );
        final PresentationSolution presentationSolution = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken
            .provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        Assertions.assertThat( presentationSolution.getItems() ).isEqualTo( listItems );
    }

    @Test
    public void shouldReturnEmptyListWhenTargeWeightLesserThanMinProductWeight()
    {

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 2, ProblemType.WEIGHT );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getItems() ).isEmpty();
    }

    @Test
    public void shouldReturnAproximatedWeightWhenTargetIs1501()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 1501, ProblemType.WEIGHT );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 1500l );
    }

    @Test
    public void shouldReturnExactWeightWhenTargetIs6500()
    {
        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 6500, ProblemType.WEIGHT );

        final PresentationSolution presentationSolution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .extract().body().as( PresentationSolution.class );
        assertThat( presentationSolution.getTotalValue() ).isEqualByComparingTo( 6500l );
    }
}
