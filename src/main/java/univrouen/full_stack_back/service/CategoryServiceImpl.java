package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private ShopService shopService;
  @Autowired private ProductService productService;

  public Category save(Category category, long productId) {
    Product product = productService.findById(productId);
    category.setProduct(product);
    Category insertedCategory = categoryRepository.save(category);
    shopService.incrementCategoryCount(product.getShop().getId());
    return insertedCategory;
  }

  @Override
  public Category findById(long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
  }

  @Override
  public Category update(long id, Category newCategory) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    return categoryRepository
        .findById(id)
        .map(
            category -> {
              category.setName(newCategory.getName());
              return categoryRepository.save(category);
            })
        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
  }

  @Override
  public void delete(long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    Product product = productService.findById(category.getProduct().getId());
    shopService.decrementCategoryCount(product.getShop().getId(), 1);
    categoryRepository.deleteById(id);
  }

  @Override
  public List<Category> findAllByProductId(long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid id supplied");
    }
    productService.findById(id);
    return categoryRepository.findAllByProductId(id);
  }
}
