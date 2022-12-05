package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Product;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Optional<Product> findById(long id);
}
