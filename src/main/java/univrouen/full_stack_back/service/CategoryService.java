package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Shop;

public interface CategoryService {
    Category save(Category category);
    Category update(long id, Category category);
}
