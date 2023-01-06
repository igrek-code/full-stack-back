package univrouen.full_stack_back.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScheduleValidator implements ConstraintValidator<ValidSchedule, String> {
  @Override
  public boolean isValid(String schedule, ConstraintValidatorContext context) {
    if (schedule == null) {
      return true;
    }
    if (!schedule.matches("^(\\d{2}-\\d{2},?)+$")) {
      return false;
    }
    String[] schedules = schedule.split(",");
    int lastHour = -1;
    for (String s : schedules) {
      String[] hours = s.split("-");
      if (Integer.parseInt(hours[0]) > 23 || Integer.parseInt(hours[1]) > 23) {
        return false;
      }
      if (Integer.parseInt(hours[0]) < 0 || Integer.parseInt(hours[1]) < 0) {
        return false;
      }
      if (Integer.parseInt(hours[0]) > Integer.parseInt(hours[1])) {
        return false;
      }
      if (Integer.parseInt(hours[0]) < lastHour) {
        return false;
      }
      lastHour = Integer.parseInt(hours[1]);
    }
    return true;
  }
}
