package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.repository.CategoryRepository;
import univrouen.full_stack_back.repository.ProductRepository;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ShopService shopService;
  @Autowired private ProductRepository productRepository;

  public Category save(Category category) {
    Category insertedCategory = categoryRepository.save(category);
    Product associatedProduct = productRepository.findById(insertedCategory.getProduct().getId()).get();
    shopService.incrementCategoryCount(associatedProduct.getShop().getId());
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
    categoryRepository.deleteById(id);
  }
}
