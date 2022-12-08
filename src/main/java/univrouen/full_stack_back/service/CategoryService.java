package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Shop;

import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    public Optional<Category> findById(long id);
    Category update(long id, Category category);
    void delete(long id);
}
