package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.ShopRepository;
import univrouen.full_stack_back.model.Shop;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements  ShopService{
    @Autowired
    private ShopRepository shopRepository;
    @Override
    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }

    @Override
    public Optional<Shop> findById(long id) {
        return shopRepository.findById(id);
    }
}
