package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name="Shop")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private long id;

    @Column(name="name")
    @NotBlank(message = "Name is mandatory")
    @Schema(example = "SoFood")
    private  String name;

    @Column(name="closed")
    @NotNull(message = "closed is mandatory")
    @Schema(example = "false")
    private  Boolean closed;

//    @Column(name="schedule")
//    @NotEmpty(message = "schedule is mandatory")
//    @Size(min=1, max=7)
//    @Schema(example = "\"lundi\": [\"9-15\"], \"mardi\": [\"6-12\", \"15-18\"]")
//    private HashMap<String, List<String>> schedule;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;
}
