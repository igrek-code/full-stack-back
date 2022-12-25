package univrouen.full_stack_back.controller;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univrouen.full_stack_back.dto.CategoryDto;
import univrouen.full_stack_back.model.Category;
import univrouen.full_stack_back.service.CategoryService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@Api(tags = "category")
public class CategoryController {
  @Autowired CategoryService categoryService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Add a new category to product")
  @ApiResponses({@ApiResponse(code = 400, message = "Bad request")})
  public CategoryDto addCategory(
      @ApiParam(value = "New category", required = true) @Valid @RequestBody(required = true)
      CategoryDto categoryDto) {
    Category category = modelMapper.map(categoryDto, Category.class);
    return modelMapper.map(categoryService.save(category), CategoryDto.class);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get store by id")
  @ApiResponses({
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Store not found")
  })
  public CategoryDto getCategory(
      @ApiParam(value = "Category id", required = true) @PathVariable(required = true) long id) throws ChangeSetPersister.NotFoundException {
  return modelMapper.map(categoryService.findById(id).orElseThrow(() -> new EntityNotFoundException("invalid category id")), CategoryDto.class);
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
