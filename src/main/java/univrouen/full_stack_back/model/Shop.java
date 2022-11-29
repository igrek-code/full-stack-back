package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name="Shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @NotBlank(message = "closed is mandatory")
    @Schema(example = "false")
    private  Boolean closed;

}
