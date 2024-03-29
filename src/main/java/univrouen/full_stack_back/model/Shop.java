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
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(readOnly = true)
  private long id;

  @Column(name = "name")
  @NotBlank(message = "Name is mandatory")
  @Schema(example = "SoFood")
  private String name;

  @Column(name = "closed")
  @NotNull(message = "closed is mandatory")
  @Schema(example = "false")
  private Boolean closed;

  @Column(updatable = false)
  private LocalDateTime creationDate;

  @Column(insertable = false, columnDefinition = "int default 0")
  @NotNull
  private int productCount;

  @Column(insertable = false, columnDefinition = "int default 0")
  @NotNull
  private int categoryCount;

  @OneToOne(cascade = CascadeType.ALL)
  @NotNull(message = "schedule is mandatory")
  @Schema(example = "\"monday\": \"9-15\", \"tuesday\": \"6-12\", \"15-18\"")
  private OpeningSchedule schedule;

  @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Product> products;

  @PrePersist
  void onCreate() {
    this.creationDate = LocalDateTime.now();
  }
}
