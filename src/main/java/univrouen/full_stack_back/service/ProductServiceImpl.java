package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.repository.CategoryRepository;
import univrouen.full_stack_back.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ShopService shopService;

    @Override
  public Product save(Product product, long shopId) {
        if (product.getPrice()<=0)
            throw new IllegalArgumentException("price must be greater then 0");
      Shop shop = shopService.findById(shopId);
      product.setShop(shop);
    Product insertedProduct = productRepository.save(product);
    shopService.incrementProductCount(insertedProduct.getShop().getId());
    return insertedProduct;
  }

  public Product findById(long id) {
      if(id <= 0) {
          throw new IllegalArgumentException("Invalid id supplied");
      }
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );
  }

  @Override
  public Product update(long id, Product newProduct) {
      if(id <= 0) {
          throw new IllegalArgumentException("Invalid id supplied");
      }
    return productRepository
        .findById(id)
        .map(
            product -> {
              product.setNameFR(newProduct.getNameFR());
              product.setNameENG(newProduct.getNameENG());
              product.setPrice(newProduct.getPrice());
              product.setDescriptionFR(newProduct.getDescriptionFR());
              product.setDescriptionENG(newProduct.getDescriptionENG());
              return productRepository.save(product);
            })
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }

  @Override
  public void delete(long id) {
      if(id <= 0) {
          throw new IllegalArgumentException("Invalid id supplied");
      }
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    int categoryCount = product.getCategories().size();
    shopService.decrementCategoryCount(product.getShop().getId(), categoryCount);
    shopService.decrementProductCount(product.getShop().getId(), 1);
    productRepository.deleteById(id);

  }

@Override
public List<Product> findAllByShopId(long shopId){
    if(shopId <= 0) {
        throw new IllegalArgumentException("Invalid id supplied");
    }
    shopService.findById(shopId);
        return productRepository.findAllByShopId(shopId);
    }

}
