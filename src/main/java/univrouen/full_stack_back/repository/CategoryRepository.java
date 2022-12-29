package univrouen.full_stack_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findAllByProductId(long id);
}
