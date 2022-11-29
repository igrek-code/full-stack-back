package univrouen.full_stack_back.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.service.ShopService;

import java.util.List;


@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Shop addShop(@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }



}
