package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShopService {
  Shop save(Shop shop);

  Optional<Shop> findById(long id);

  List<Shop> findAll();

  Shop update(Long shopId, Shop shop);

  void delete(Long id);


  List<Product> findProductsByShopIdAndCategoryName(long id, String category);

  List<Shop> findByClosedAndCreationDateBetween(boolean closed, Date start, Date end);
  void incrementProductCount(long id);

  void decrementProductCount(long id, int count);

  void incrementCategoryCount(long id);
  void decrementCategoryCount(long id, int count);
}
