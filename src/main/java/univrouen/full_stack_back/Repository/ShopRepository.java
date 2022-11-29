package univrouen.full_stack_back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Shop;

public interface ShopRepository extends JpaRepository<Shop,Integer> {
}
