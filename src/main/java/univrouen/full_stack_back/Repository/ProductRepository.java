package univrouen.full_stack_back.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByShopId(long id, Pageable pageable);

  List<Product> findAllByShopIdAndCategories_Name(long id, String category);
}
