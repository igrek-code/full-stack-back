package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.CategoryRepository;
import univrouen.full_stack_back.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id, Category newCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepository.save(category);
                }).orElseThrow(()->new RuntimeException("Category does not exist!"));
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
