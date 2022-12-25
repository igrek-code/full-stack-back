package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.dto.ProductDto;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
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
  @ApiResponses({@ApiResponse(code = 400, message = "Bad request")})
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
//  TODO 1: response entity ?
  public ProductDto getProduct(
      @ApiParam(value = "Product id", required = true) @PathVariable(required = true) Long id) {
    return modelMapper.map(productService.findById(id).orElseThrow(), ProductDto.class);
  }

  @PutMapping(
      path = "/{id}/description",
      consumes = "application/json",
      produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Add product description by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Product not found"),
    @ApiResponse(code = 405, message = "Validation exception")
  })
  public ProductDto addProductDescription(
      @ApiParam(value = "Product id to add description", required = true)
          @PathVariable(required = true)
          Long id,
      @ApiParam(value = "Product description", required = true) @RequestBody
          HashMap<String, String> description) {
    return modelMapper.map(productService.addDescription(id, description), ProductDto.class);
  }

  //    TODO gerer les exceptions
  @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update product by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Product not found"),
    @ApiResponse(code = 405, message = "Validation exception")
  })
  public ProductDto updateProduct(
      @ApiParam(value = "Product id to modify", required = true) @PathVariable(required = true)
          long id,
      @ApiParam(value = "New product information", required = true) @RequestBody ProductDto productDto) {
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

  @GetMapping(path = "/{id}/categories", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get category by product id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "product not found")
  })
  public List<Category> getProductCategories(
      @ApiParam(value = "product id", required = true) @PathVariable(required = true) Long id,
      @ApiParam(value = "Page number", required = true) @RequestParam int page,
      @ApiParam(value = "Number of categories in the page", required = true) @RequestParam
          int size) {
    return productService.findCategoriesById(id, page, size);
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get products by shop id")
  @ApiResponses({
          @ApiResponse(code = 400, message = "Invalid shop id supplied"),
          @ApiResponse(code = 404, message = "No product found")
  })
  public List<ProductDto> getProductsByShopId(
          @ApiParam(value = "product id", required = true) @Param("shopId") Long shopId) {
    return productService.findAllByShopId(shopId).stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
  }

}
