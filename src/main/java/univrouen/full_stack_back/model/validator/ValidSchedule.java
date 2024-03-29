package univrouen.full_stack_back.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScheduleValidator.class)
public @interface ValidSchedule {
  String message() default "Invalid schedule format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
