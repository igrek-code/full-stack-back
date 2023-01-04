package univrouen.full_stack_back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String sunday;
    @Column
    private String monday;
    @Column
    private String tuesday;
    @Column
    private String wednesday;
    @Column
    private String thursday;
    @Column
    private String friday;
    @Column
    private String saturday;
}
