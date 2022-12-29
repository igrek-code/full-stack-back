package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
  Product save(Product product, long shopId);

  Optional<Product> findById(long id);

  public Product update(long id, Product newProduct);

  void delete(long id);

  List<Product> findAllByShopId(long shopId);
}
