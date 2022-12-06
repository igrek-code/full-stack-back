package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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


    @PutMapping(path="/{id}/description",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add product description by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 405, message = "Validation exception")
    })
    public Product addProductDescription(
            @ApiParam(value = "Product id to add description", required = true)
            @PathVariable (required = true) Long id,
            @ApiParam(value = "Product description", required = true)
            @RequestBody HashMap<String, String> description)
    {
        return productService.addDescription(id, description);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get products with pagination")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Page or size missing"),
            @ApiResponse(code = 500, message = "Page or size out of bound")
    })
    public List<Product> getProducts(
            @ApiParam(value = "Page number", required = true)
            @RequestParam int page,
            @ApiParam(value = "Number of products in the page", required = true)
            @RequestParam int size){
        return productService.findAll(page, size);
    }

//    TODO gerer les exceptions
    @PutMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 405, message = "Validation exception")
    })
    public Product updateProduct(
            @ApiParam(value = "Product id to modify", required = true)
            @PathVariable (required = true) long id,
            @ApiParam(value = "New product information", required = true)
            @RequestBody Product product)
    {
        return productService.update(id,product);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "delete product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid id supplied")
    })
    private void delete(
            @ApiParam(value = "product id to delete", required = true)
            @PathVariable("id") long id)
    {
        productService.delete(id);
    }
}
