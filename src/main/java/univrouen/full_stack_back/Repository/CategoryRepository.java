package univrouen.full_stack_back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
