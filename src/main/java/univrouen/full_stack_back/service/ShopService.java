package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
  Shop save(Shop shop);

  Optional<Shop> findById(long id);

  List<Shop> findAll();

  Shop update(Long shopId, Shop shop);

  void delete(Long id);
  void incrementProductCount(long id);

  void decrementProductCount(long id, int count);

  void incrementCategoryCount(long id);
  void decrementCategoryCount(long id, int count);
}
