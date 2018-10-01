package br.com.neolog.ecommerce.product;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.category.Category;
import io.restassured.http.ContentType;

public class ProductIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void sholdReturnNameOfFirstProductOfList() {

		final Category eletronico = new Category(1, 1, "Eletrônicos");
		final Product product1 = new Product(3, 3, "TV", 2500.0, "PlasmaTron", 1.5, eletronico);
		final String json = "{\"id\": 0,\"cod\": 3,\"name\": \"TV\",\"price\": 2500.0,\"description\": \"PlasmaTron\",\"weight\": 1.5,\"category\":{\"id\":1,\"cod\":3,\"name\":\"Eletrônicos\"}}";
		final Product x = given().contentType(ContentType.JSON).body(json).when().post("product/save")
				.as(Product.class);
		assertThat(x).isEqualTo(product1);
	}

	@Test
	public void shouldReturnNameOfSecondItemInTheList() {

		given().contentType(ContentType.JSON).when().get("product/search/name/2").then().log().everything().assertThat()
				.statusCode(HttpStatus.SC_OK).and().body(equalTo("Windão Original"));
	}

	@Test
	public void shouldReturnTrueWhenSendOneProductViaPostAndRecieveSameProductInResponse() {

		final Category eletronico = new Category(1, 1, "Eletrônicos");
		final Product product1 = new Product(1, 1, "TV", 2500.0, "PlasmaTron", 1.5, eletronico);
		final Product x = given().contentType(ContentType.JSON).body(product1).when().get("product/search/id/1")
				.as(Product.class);
		assertThat(x).isEqualTo(product1);
	}

	@Test
	public void shouldReturnTrueWhenSendAListOfProductsAndRecieveTheSameList() {
		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Category tecnologico = new Category(2, 2, "Tecnológicos");
		final Product product1 = new Product(1, 1, "TV", 2500.0, "PlasmaTron", 1.5, eletronico);
		final Product product2 = new Product(2, 2, "Windão Original", 2500.0, "S.O", 5000.0, tecnologico);
		final Product product3 = new Product(3, 3, "TV", 2500.0, "PlasmaTron", 1.5, eletronico);
		final List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		final List<Product> body = given().contentType(ContentType.JSON).when().get("product/search/").then().extract()
				.jsonPath().getList("", Product.class);
		assertThat(body).isEqualTo(products);
	}

}
