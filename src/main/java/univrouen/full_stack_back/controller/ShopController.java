package univrouen.full_stack_back.controller;



import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.service.ShopService;

import javax.validation.Valid;
import java.util.Optional;


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
        return shopService.save(shop);
    }

    @GetMapping(path="/{id}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get store by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Store not found")
    })
    public Optional<Shop> getShop(
            @ApiParam(value = "Store id", required = true)
            @PathVariable(required = true) Long id){
        return shopService.findById(id);
    }


    @PutMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update store by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Store not found"),
            @ApiResponse(code = 405, message = "Validation exception")
    })
    public Shop update(
            @ApiParam(value = "Store id to modify", required = true)
            @PathVariable (required = true) Long id,
            @ApiParam(value = "New store information", required = true)
            @RequestBody Shop shop)
    {
        return shopService.update(id,shop);
    }

    @DeleteMapping(path="/{id}",produces = "",consumes="")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "delete store by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied")
    })
    private void delete(
            @ApiParam(value = "Store id to delete", required = true)
            @PathVariable("id") long id)
    {
        shopService.delete(id);
    }


}
