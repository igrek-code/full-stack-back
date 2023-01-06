package univrouen.full_stack_back.dto;

import lombok.Data;
import univrouen.full_stack_back.model.Category;

import java.util.List;

@Data
public class ProductDto {
  private long id;
  private String nameFR;
  private String nameENG;
  private double price;
  private String descriptionFR;
  private String descriptionENG;
  private List<Category> categories;
}
