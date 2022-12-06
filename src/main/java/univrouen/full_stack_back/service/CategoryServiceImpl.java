package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.CategoryRepository;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Shop;

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
                .map(shop -> {
                    shop.setName(newCategory.getName());
                    return categoryRepository.save(shop);
                }).orElseThrow(() -> new RuntimeException("shop does not exist!"));

    }
}
