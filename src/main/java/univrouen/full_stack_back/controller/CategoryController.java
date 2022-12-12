package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.service.CategoryService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@Api(tags = "category")
public class CategoryController {
  @Autowired CategoryService categoryService;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Add a new category to product")
  @ApiResponses({@ApiResponse(code = 400, message = "Bad request")})
  public Category addCategory(
      @ApiParam(value = "New category", required = true) @Valid @RequestBody(required = true)
          Category category) {
    return categoryService.save(category);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public Optional<Category> getCategory(
      @ApiParam(value = "Category id", required = true) @PathVariable(required = true) long id) {
    return categoryService.findById(id);
  }

  @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update category by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Category not found"),
    @ApiResponse(code = 405, message = "Validation exception")
  })
  public Category updateCategory(
      @ApiParam(value = "Category id to modify", required = true) @PathVariable(required = true)
          Long id,
      @ApiParam(value = "New category information", required = true) @RequestBody
          Category category) {
    return categoryService.update(id, category);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "delete store by id")
  @ApiResponses({@ApiResponse(code = 400, message = "Invalid id supplied")})
  private void deleteCategory(
      @ApiParam(value = "Category id to delete", required = true) @PathVariable("id") long id) {
    categoryService.delete(id);
  }
}
