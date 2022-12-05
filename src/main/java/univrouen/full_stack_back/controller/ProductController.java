package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Api(tags="product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new product to store")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request") })
    public Product addProduct(
            @ApiParam(value = "New product", required = true)
            @Valid @RequestBody(required = true) Product product) {
        return productService.save(product);
    }

    @GetMapping(path="/{id}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public Optional<Product> getProduct(
            @ApiParam(value = "Product id", required = true)
            @PathVariable(required = true) Long id){
        return productService.findById(id);
    }

    @PutMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 405, message = "Validation exception")
    })
    public Product update(
            @ApiParam(value = "Product id to modify", required = true)
            @PathVariable (required = true) Long id,
            @ApiParam(value = "New product information", required = true)
            @RequestBody Product product)
    {
        return productService.update(id,product);
    }
}
