package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "Name is mandatory")
    @Schema(example = "tacos")
    private String name;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    @NotNull(message = "shop is mandatory")
//    TODO add schema to shop in product
    private Shop shop;
}
