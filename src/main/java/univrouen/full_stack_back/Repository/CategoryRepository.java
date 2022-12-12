package univrouen.full_stack_back.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findAllByProductId(long id, Pageable pageable);
}
