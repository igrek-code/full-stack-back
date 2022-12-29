package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  @NotBlank(message = "Name is mandatory")
  @Schema(example = "plat principal")
  private String name;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonBackReference
  //    TODO add schema to shop in product
  private Product product;
}
