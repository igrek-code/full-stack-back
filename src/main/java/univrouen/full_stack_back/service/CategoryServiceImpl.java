package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.repository.CategoryRepository;
import univrouen.full_stack_back.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ShopService shopService;
  @Autowired private ProductRepository productRepository;

  public Category save(Category category, long productId) {
    // Find the shop
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (!optionalProduct.isPresent()) {
      throw new EntityNotFoundException("Product not found");
    }
    Product product = optionalProduct.get();
    category.setProduct(product);
    Category insertedCategory = categoryRepository.save(category);
    shopService.incrementCategoryCount(product.getShop().getId());
    return insertedCategory;
  }

  @Override
  public Optional<Category> findById(long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public Category update(long id, Category newCategory) {
    return categoryRepository
        .findById(id)
        .map(
            category -> {
              category.setName(newCategory.getName());
              return categoryRepository.save(category);
            })
        .orElseThrow(() -> new RuntimeException("Category does not exist!"));
  }

  @Override
  public void delete(long id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category does not exist!"));
    Product product = productRepository.findById(category.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product does not exist!"));
    shopService.decrementCategoryCount(product.getShop().getId(), 1);
    categoryRepository.deleteById(id);
  }

  @Override
  public List<Category> findAllByProductId(long id) {
    return categoryRepository.findAllByProductId(id);
  }
}
