package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@Api(tags="category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new category to product")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request") })
    public Category addCategory(
            @ApiParam(value = "New category", required = true)
            @Valid @RequestBody(required = true) Category category) {
        return categoryService.save(category);
    }
}
