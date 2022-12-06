package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    Shop save(Shop shop);
    Optional<Shop> findById(long id);
    Shop update(Long shopId,Shop shop);
    void delete(Long id);
    List<Product> findProductsById(long id, int page, int size);
}
