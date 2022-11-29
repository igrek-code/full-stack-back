package univrouen.full_stack_back.service;

import univrouen.full_stack_back.model.Shop;

import java.util.List;

public interface ShopService {
    Shop addShop(Shop shop);
    List<Shop> showShops();
}
