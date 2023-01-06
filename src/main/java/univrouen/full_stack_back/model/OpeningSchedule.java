package univrouen.full_stack_back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univrouen.full_stack_back.model.validator.ValidSchedule;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column @ValidSchedule private String sunday;
  @Column @ValidSchedule private String monday;
  @Column @ValidSchedule private String tuesday;
  @Column @ValidSchedule private String wednesday;
  @Column @ValidSchedule private String thursday;
  @Column @ValidSchedule private String friday;
  @Column @ValidSchedule private String saturday;
}
