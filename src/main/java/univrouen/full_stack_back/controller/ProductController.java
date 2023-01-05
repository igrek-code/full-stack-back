package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.dto.ProductDto;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Api(tags = "product")
public class ProductController {
  @Autowired ProductService productService;
  @Autowired
  ModelMapper modelMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Add a new product to store")
  @ApiResponses({
          @ApiResponse(code = 400, message = "Bad request"),
          @ApiResponse(code = 404, message = "Shop not found")
  })
  public ProductDto addProduct(
      @ApiParam(value = "New product", required = true) @Valid @RequestBody(required = true)
          ProductDto productDto,
      @Param("shopId") long shopId) {
    Product product = modelMapper.map(productDto, Product.class);
    return modelMapper.map(productService.save(product, shopId), ProductDto.class);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get product by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Product not found")
  })
  public ProductDto getProduct(
      @ApiParam(value = "Product id", required = true) @PathVariable(required = true) Long id) {
    return modelMapper.map(productService.findById(id), ProductDto.class);
  }

  @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update product by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Bad request"),
    @ApiResponse(code = 404, message = "Product not found")
  })
  public ProductDto updateProduct(
      @ApiParam(value = "Product id to modify", required = true) @PathVariable(required = true)
          long id,
      @ApiParam(value = "New product information", required = true) @Valid @RequestBody ProductDto productDto) {
    Product product = modelMapper.map(productDto, Product.class);
  return modelMapper.map(productService.update(id, product), ProductDto.class);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "delete product by id")
  @ApiResponses({@ApiResponse(code = 400, message = "Invalid id supplied")})
  private void delete(
      @ApiParam(value = "product id to delete", required = true) @PathVariable("id") long id) {
    productService.delete(id);
  }


  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get products by shop id")
  @ApiResponses({
          @ApiResponse(code = 400, message = "Invalid id supplied"),
          @ApiResponse(code = 404, message = "Shop not found")
  })
  public List<ProductDto> getProductsByShopId(
          @ApiParam(value = "Store id", required = true) @Param("shopId") Long shopId) {
    return productService.findAllByShopId(shopId).stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
  }

}
