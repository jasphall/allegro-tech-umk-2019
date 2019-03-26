package pl.sikorski.demoapp.domain;

public interface ProductFacade {
    ProductResponseDto get(String id);

    ProductResponseDto create(ProductRequestDto productRequest);

    // update
    // delete
}
