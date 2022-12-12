package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.service.ShopService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
@Api(tags = "shop")
public class ShopController {
  @Autowired ShopService shopService;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Add a new store")
  @ApiResponses({@ApiResponse(code = 400, message = "Bad request")})
  public Shop addShop(
      @ApiParam(value = "New store", required = true) @Valid @RequestBody(required = true)
          Shop shop) {
    return shopService.save(shop);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public Optional<Shop> getShop(
      @ApiParam(value = "Store id", required = true) @PathVariable(required = true) Long id) {
    return shopService.findById(id);
  }

  @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found"),
    @ApiResponse(code = 405, message = "Validation exception")
  })
  public Shop updateShop(
      @ApiParam(value = "Store id to modify", required = true) @PathVariable(required = true)
          Long id,
      @ApiParam(value = "New store information", required = true) @RequestBody Shop shop) {
    return shopService.update(id, shop);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "delete store by id")
  @ApiResponses({@ApiResponse(code = 400, message = "Invalid id supplied")})
  private void deleteShop(
      @ApiParam(value = "Store id to delete", required = true) @PathVariable("id") long id) {
    shopService.delete(id);
  }

  @GetMapping(path = "/{id}/products", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get products by store id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public List<Product> getShopProducts(
      @ApiParam(value = "Store id", required = true) @PathVariable(required = true) Long id,
      @ApiParam(value = "Page number", required = true) @RequestParam int page,
      @ApiParam(value = "Number of products in the page", required = true) @RequestParam int size) {
    return shopService.findProductsByShopId(id, page, size);
  }

  @GetMapping(path = "/{id}/products/findByCategory", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get products by category and store id")
  @ApiResponses({
          @ApiResponse(code = 400, message = "Invalid id supplied"),
          @ApiResponse(code = 404, message = "Store not found")
  })
  public List<Product> getShopProductsByCategory(
          @ApiParam(value = "Store id", required = true) @PathVariable(required = true) Long id,
          @ApiParam(value = "Category name", required = true) @RequestParam String category) {
  return shopService.findProductsByShopIdAndCategoryName(id, category);
  }
}
