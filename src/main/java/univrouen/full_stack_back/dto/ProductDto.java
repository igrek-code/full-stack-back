package univrouen.full_stack_back.dto;

import lombok.Data;

@Data
public class ProductDto {
    private long id;
    private String name;
    private double price;
    private String description;
//    private List<Category> categories;
}
