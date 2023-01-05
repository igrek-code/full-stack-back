package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.repository.ProductRepository;
import univrouen.full_stack_back.repository.ShopRepository;

import javax.persistence.EntityNotFoundException;
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
              shop.setSchedule(newShop.getSchedule());
              return shopRepository.save(shop);
            })
        .orElseThrow(() -> new EntityNotFoundException("Store not found"));
  }

  @Override
  public void delete(Long id) {
    if (!shopRepository.existsById(id)) {
      throw new EntityNotFoundException("Store not found");
    }
    shopRepository.deleteById(id);
  }

    @Override
  public void incrementProductCount(long id) {
    shopRepository.findById(id).map(shop -> {
      shop.setProductCount(shop.getProductCount() + 1);
      return shopRepository.save(shop);
    }).orElseThrow(() -> new RuntimeException("shop does not exist!"));
  }

  @Override
  public void decrementProductCount(long id, int count) {
    shopRepository.findById(id).map(shop -> {
      shop.setProductCount(shop.getProductCount() - count);
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

  @Override
  public void decrementCategoryCount(long id, int count) {
    shopRepository.findById(id).map(shop -> {
      shop.setCategoryCount(shop.getCategoryCount() - count);
      return shopRepository.save(shop);
    }).orElseThrow(() -> new RuntimeException("shop does not exist!"));
  }

}
