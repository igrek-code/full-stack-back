package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Product;

import java.util.HashMap;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Product addDescription(long id, HashMap<String, String> description);
    Optional<Product> findById(long id);
}
