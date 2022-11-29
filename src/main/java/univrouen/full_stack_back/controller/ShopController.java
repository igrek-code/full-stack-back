package univrouen.full_stack_back.controller;



import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.service.ShopService;

import javax.validation.Valid;


@RestController
@RequestMapping("/shop")
@Api(tags="shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new store")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request") })
    public Shop addShop(
            @ApiParam(value = "New store", required = true)
            @Valid @RequestBody(required = true) Shop shop) {
        return shopService.addShop(shop);
    }


}
