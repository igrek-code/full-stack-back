package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  @NotBlank(message = "French name is mandatory")
  private String nameFR;

  @Column
  @NotBlank(message = "English name is mandatory")
  private String nameENG;

  @Column
  @NotNull(message = "price is mandatory")
  @Schema(example = "5.40")
  private double price;

  @Column
  @Schema(example = "description en français")
  private String descriptionFR;

  @Column
  @Schema(example = "description in english")
  private String descriptionENG;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shop_id")
  @JsonBackReference
  @NotNull(message = "shop is mandatory")
  private Shop shop;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Category> categories;
}
