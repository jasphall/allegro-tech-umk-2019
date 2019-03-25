package pl.sikorski.demoapp.api;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import pl.sikorski.demoapp.IntegrationTest;
import pl.sikorski.demoapp.domain.ProductRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductEndpointTest extends IntegrationTest {

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
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("\"name\":\"czerwona sukienka\"");
    }

//    @Test
//    public void shouldCreateProduct() {
//        // in order to make it work you should add @JsonCreator/@JsonProperty annotations to response too
//
//        // given
//        var url = "http://localhost:" + port + "/products";
//        var productRequest = new ProductRequestDto("czerwona sukienka");
//        var productRequestJson = mapToJson(productRequest);
//        var httpRequest = getHttpRequest(productRequestJson);
//
//        // when
//        ResponseEntity<ProductResponseDto> response = httpClient.postForEntity(url, httpRequest, ProductResponseDto.class);
//
//        // then
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//        assertThat(response.getBody().getName()).isEqualTo("czerwona sukienka");
//    }

    private HttpEntity<String> getHttpRequest(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");

        return new HttpEntity<>(json, headers);
    }

}
