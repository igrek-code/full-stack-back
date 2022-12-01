package univrouen.full_stack_back.service;

import org.springframework.web.bind.annotation.PathVariable;
import univrouen.full_stack_back.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    Shop save(Shop shop);
    Optional<Shop> findById(long id);
    Shop update(Long shopId,Shop shop);
    void delete(Long id);
}
