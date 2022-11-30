package univrouen.full_stack_back.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;

@Table(name="Shop")
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private int id;

    @Column(name="name")
    @NotBlank(message = "Name is mandatory")
    @Schema(example = "SoFood")
    private  String name;

    @Column(name="closed")
    @NotBlank(message = "closed is mandatory")
    @Schema(example = "false")
    private  Boolean closed;
    @Column(name="opening_time")
    @NotBlank(message = "opening_time is mandatory")
    private ArrayList<Date> opening_time = new ArrayList(6);
    @Column(name="closing_time")
    @NotBlank(message = "closing_time is mandatory")
    private ArrayList<Date>  closing_time = new ArrayList(6);

}
