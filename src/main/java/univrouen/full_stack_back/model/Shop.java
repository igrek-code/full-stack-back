package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.*;

@Table(name="Shop")
@Getter
@Setter
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

    @Column(name="schedule")
    @NotEmpty(message = "schedule is mandatory")
    @Size(min=1, max=7)
    @Schema(example = "\"lundi\": [\"9-15\"], \"mardi\": [\"6-12\", \"15-18\"]")
    private HashMap<String, List<String>> schedule;
}
