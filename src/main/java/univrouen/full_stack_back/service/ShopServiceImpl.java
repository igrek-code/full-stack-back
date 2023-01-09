package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.repository.ProductRepository;
import univrouen.full_stack_back.repository.ShopRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
  @Autowired private ShopRepository shopRepository;

  @Autowired private ProductRepository productRepository;
  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public Shop save(Shop shop) {

    return shopRepository.save(shop);
  }

  @Override
  public Shop findById(long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    return shopRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Store not found"));
  }

  @Override
  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  @Override
  public Shop update(Long id, Shop newShop) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    return shopRepository
        .findById(id)
        .map(
            shop -> {
              shop.setName(newShop.getName());
              shop.setClosed(newShop.getClosed());
              shop.setSchedule(newShop.getSchedule());
              return shopRepository.save(shop);
            })
        .orElseThrow(() -> new EntityNotFoundException("Store not found"));
  }

  @Override
  public void delete(Long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    if (!shopRepository.existsById(id)) {
      throw new EntityNotFoundException("Store not found");
    }
    shopRepository.deleteById(id);
  }

  @Override
  public void incrementProductCount(long id) {
    shopRepository
        .findById(id)
        .map(
            shop -> {
              shop.setProductCount(shop.getProductCount() + 1);
              return shopRepository.save(shop);
            })
        .orElseThrow(() -> new EntityNotFoundException("shop does not exist!"));
  }

  @Override
  public void decrementProductCount(long id, int count) {
    shopRepository
        .findById(id)
        .map(
            shop -> {
              shop.setProductCount(shop.getProductCount() - count);
              return shopRepository.save(shop);
            })
        .orElseThrow(() -> new EntityNotFoundException("shop does not exist!"));
  }

  @Override
  public void updateCategoryCount(long id) {
    String updateQuery =
        "UPDATE shop SET category_count = (SELECT COUNT(DISTINCT name) FROM category WHERE product_id IN (SELECT id FROM product WHERE shop_id = shop.id))";
    jdbcTemplate.update(updateQuery);
  }
}
