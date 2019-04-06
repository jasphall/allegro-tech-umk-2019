package pl.sikorski.demoapp.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import pl.sikorski.demoapp.IntegrationTest;
import pl.sikorski.demoapp.domain.ProductFacade;
import pl.sikorski.demoapp.domain.ProductRequestDto;
import pl.sikorski.demoapp.domain.ProductResponseDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class ProductEndpointTest extends IntegrationTest {

    @Autowired
    ProductFacade productFacade;

    @Test
    public void shouldCreateProduct() {
        // given
        var url = "http://localhost:" + port + "/products";
        var productRequest = new ProductRequestDto("czerwona sukienka");
        var productRequestJson = mapToJson(productRequest);
        var httpRequest = getHttpRequest(productRequestJson);

        // when
        ResponseEntity<String> response = httpClient.postForEntity(url, httpRequest, String.class);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(OK);
        assertThat(response.getBody()).contains("\"name\":\"czerwona sukienka\"");
    }

    @Test
    public void shouldGetProduct() {
        // given
        var product = new ProductRequestDto("czerwona sukienka");
        var createdProduct = productFacade.create(product);
        var url = "http://localhost:" + port + "/products/";

        //when
        ResponseEntity<ProductResponseDto> response = httpClient.getForEntity(url + createdProduct.getId(), ProductResponseDto.class);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(createdProduct);
    }

    @Test
    public void shouldGetNotFoundWhenProductIsNotAvailable() {
        //given
        var url = "http://localhost:" + port + "/products/dummyProductId";

        //when
        ResponseEntity<String> response = httpClient.getForEntity(url, String.class);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("There is no product with id: dummyProductId");
    }

    private HttpEntity<String> getHttpRequest(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");

        return new HttpEntity<>(json, headers);
    }

}
