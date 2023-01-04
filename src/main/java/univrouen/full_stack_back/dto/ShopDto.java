package univrouen.full_stack_back.dto;

import lombok.Data;
import univrouen.full_stack_back.model.OpeningSchedule;

import java.time.LocalDateTime;

@Data
public class ShopDto {
    private long id;
    private String name;
    private Boolean closed;
    private LocalDateTime creationDate;
    private int productCount;
    private int categoryCount;
    private OpeningSchedule schedule;
}
