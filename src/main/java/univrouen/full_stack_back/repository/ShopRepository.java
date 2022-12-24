package univrouen.full_stack_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univrouen.full_stack_back.model.Shop;

import java.util.Date;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
  List<Shop> findByClosedAndCreationDateBetween(boolean closed, Date start, Date end);
}
