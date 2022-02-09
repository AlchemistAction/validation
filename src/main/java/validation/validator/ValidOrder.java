package validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidOrderValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrder {
    String message() default "total price must be 50 or greater for online order. " +
            "Found: ${validatedValue.totalPrice}";

    String field() default " ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
