package br.com.neolog.ecommerce.product;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.productcategory.Category;
import io.restassured.http.ContentType;

public class ProductIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void sholdReturnNameOfFirstProductOfList() {

		final String json = "{\"id\": 1,\"cod\": 21,\"name\": \"TV\",\"price\": 2000.00,\"description\": \"Super Tela\",\"weight\": 2.500,\"category\":{\"id\":1,\"cod\":212,\"name\":\"Eletronico\"}}";
		given().contentType("application/json").body(json).when().post("product/setName").then().log().everything()
				.assertThat().statusCode(HttpStatus.SC_OK).and().body(equalTo("TV"));
	}

	@Test
	public void shouldReturnNameOfSecondItemInTheList() {

		given().contentType(ContentType.JSON).when().get("product/search/name/1").then().log().everything().assertThat()
				.statusCode(HttpStatus.SC_OK).and().body(equalTo("Windão Original"));
	}

	@Test
	public void shouldReturnTrueWhenSendOneProductViaPostAndRecieveSameProductInResponse() {

		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Product product1 = new Product(1, 1, "TV", 2500.00, "Plasma", 2.500, eletronico);
		final Product x = given().contentType(ContentType.JSON).body(product1).when().get("product/search/0")
				.as(Product.class);

		assertThat(x).isEqualTo(product1);
	}

	@Test
	public void shouldReturnTrueWhenSendAListOfProductsAndRecieveTheSameList() {
		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Category tecnologico = new Category(2, 2, "Tecnologia");

		final Product product1 = new Product(1, 1, "TV", 2500.00, "Plasma", 2.500, eletronico);
		final Product product2 = new Product(2, 2, "Windão Original", 10000.00, "S.O 'BOM' ", 10.00, tecnologico);
		final Product product3 = new Product(3, 3, "Linux", 0.00, "S.O", 0.00, tecnologico);

		final List<Product> products = new ArrayList<Product>();

		products.add(product1);
		products.add(product2);
		products.add(product3);

		final List<Product> body = given().contentType(ContentType.JSON).when().get("product/search/").then().extract()
				.jsonPath().getList("", Product.class);
		assertThat(body).isEqualTo(products);

	}

}
