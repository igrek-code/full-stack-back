package univrouen.full_stack_back.dto;

import lombok.Data;
import univrouen.full_stack_back.model.Product;

@Data
public class CategoryDto {
    private long id;
    private String name;
    private Product product;
}
