package pl.sikorski.demoapp.infrastructure;

import pl.sikorski.demoapp.domain.Product;

public interface ProductRepository {

    void save(Product product);

    Product findById(String id);

}
