package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Product;
import univrouen.full_stack_back.service.ProductService;

import javax.validation.Valid;

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
}
