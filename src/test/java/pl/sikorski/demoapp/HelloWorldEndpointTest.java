package pl.sikorski.demoapp;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldEndpointTest extends IntegrationTest {

    @Test
    public void shouldReturnGreetings() {
        // given
        final String url = "http://localhost:" + port + "/hello";

        // when
        ResponseEntity<String> response = httpClient.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Hello World!");
    }

}
