package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.dto.ShopDto;
import univrouen.full_stack_back.model.Shop;
import univrouen.full_stack_back.service.ShopService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@Api(tags = "shop")
public class ShopController {
  @Autowired ShopService shopService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Add a new store")
  @ApiResponses({@ApiResponse(code = 400, message = "Bad request")})
  public Shop addShop(
      @ApiParam(value = "New store", required = true) @Valid @RequestBody(required = true) ShopDto shopDto)  {
    Shop shop = modelMapper.map(shopDto, Shop.class);
    return shopService.save(shop);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public ShopDto getShop(
      @ApiParam(value = "Store id", required = true)
      @PathVariable(required = true)
      Long id) {
    Shop shop = shopService.findById(id);
    ShopDto shopDto = modelMapper.map(shop, ShopDto.class);
    return shopDto;
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all shops")
  public List<ShopDto> getShops() {
    return shopService.findAll()
            .stream()
            .map(shop -> modelMapper.map(shop, ShopDto.class))
            .collect(Collectors.toList());
  }

  @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Bad request"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public ShopDto updateShop(
      @ApiParam(value = "Store id to modify", required = true) @PathVariable(required = true)
          Long id,
      @ApiParam(value = "New store information", required = true) @RequestBody @Valid ShopDto shopDto) {

    Shop shopRequest = modelMapper.map(shopDto, Shop.class);
     Shop shop = shopService.update(id, shopRequest);
     return modelMapper.map(shop, ShopDto.class);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "delete store by id")
  @ApiResponses({@ApiResponse(code = 400, message = "Invalid id supplied")})
  private void deleteShop(
      @ApiParam(value = "Store id to delete", required = true) @PathVariable("id") long id) {

    shopService.delete(id);
  }
}
