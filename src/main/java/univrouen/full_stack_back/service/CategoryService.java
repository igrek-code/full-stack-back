package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Category;

import java.util.List;

public interface CategoryService {
  Category save(Category category, long productId);

  public Category findById(long id);

  Category update(long id, Category category);

  void delete(long id);

  List<Category> findAllByProductId(long id);
}
