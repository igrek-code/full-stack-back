package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.ProductRepository;
import univrouen.full_stack_back.Repository.ShopRepository;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
  @Autowired private ShopRepository shopRepository;

  @Autowired private ProductRepository productRepository;

  @Override
  public Shop save(Shop shop) {
    return shopRepository.save(shop);
  }

  @Override
  public Optional<Shop> findById(long id) {
    return shopRepository.findById(id);
  }

  @Override
  public Shop update(Long id, Shop newShop) {
    return shopRepository
        .findById(id)
        .map(
            shop -> {
              shop.setName(newShop.getName());
              shop.setClosed(newShop.getClosed());
              return shopRepository.save(shop);
            })
        .orElseThrow(() -> new RuntimeException("shop does not exist!"));
  }

  @Override
  public void delete(Long id) {
    shopRepository.deleteById(id);
  }

  @Override
  public List<Product> findProductsByShopId(long id, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return productRepository.findAllByShopId(id, pageable);
  }

  @Override
  public List<Product> findProductsByShopIdAndCategoryName(long id, String category) {
    return productRepository.findAllByShopIdAndCategories_Name(id, category);
  }
}
