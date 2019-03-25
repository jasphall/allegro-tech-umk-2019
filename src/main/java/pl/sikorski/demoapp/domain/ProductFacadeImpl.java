package pl.sikorski.demoapp.domain;

import org.springframework.stereotype.Component;
import pl.sikorski.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
class ProductFacadeImpl implements ProductFacade {

    private final ProductRepository productRepository;

    ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        if (!productRequest.isValid()) {
            throw new RuntimeException("Product name cannot be empty!");
        }

        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createdAt);

        productRepository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }

}
