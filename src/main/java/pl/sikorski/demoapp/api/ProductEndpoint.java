package pl.sikorski.demoapp.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sikorski.demoapp.domain.ProductFacade;
import pl.sikorski.demoapp.domain.ProductRequestDto;
import pl.sikorski.demoapp.domain.ProductResponseDto;

@RestController
@RequestMapping("/products")
class ProductEndpoint {

    private final ProductFacade productFacade;

    ProductEndpoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productFacade.create(productRequestDto);
    }

}
