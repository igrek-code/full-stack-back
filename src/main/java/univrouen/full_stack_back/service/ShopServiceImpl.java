package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.ShopRepository;
import univrouen.full_stack_back.model.Shop;

import java.util.List;

@Service
public class ShopServiceImpl implements  ShopService{
    @Autowired
    private ShopRepository shopRepository;
    @Override
    public Shop addShop(Shop shop){
        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> showShops() {
        return shopRepository.findAll();
    }
}
