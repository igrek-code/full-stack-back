package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.repository.ProductRepository;
import univrouen.full_stack_back.repository.ShopRepository;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;

import java.util.Date;
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
  public List<Shop> findAll() {
    return shopRepository.findAll();
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

  @Override
  public List<Shop> findByClosedAndCreationDateBetween(boolean closed, Date start, Date end) {
    return shopRepository.findByClosedAndCreationDateBetween(closed, start, end);
  }

    @Override
  public void incrementProductCount(long id) {
    shopRepository.findById(id).map(shop -> {
      shop.setProductCount(shop.getProductCount() + 1);
      return shopRepository.save(shop);
    }).orElseThrow(() -> new RuntimeException("shop does not exist!"));
  }

  @Override
  public void incrementCategoryCount(long id) {
    shopRepository.findById(id).map(shop -> {
      shop.setCategoryCount(shop.getCategoryCount() + 1);
      return shopRepository.save(shop);
    }).orElseThrow(() -> new RuntimeException("shop does not exist!"));
  }

}
