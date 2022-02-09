package validation.validator;

import validation.model.UserOrder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidOrderValidator implements ConstraintValidator<ValidOrder, UserOrder> {

    @Override
    public void initialize(ValidOrder constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserOrder userOrder, ConstraintValidatorContext constraintValidatorContext) {
        if (userOrder.getAmount() == null || userOrder.getQuantity() == null) {
            return false;
        }
        return userOrder.getTotalPrice()
                .compareTo(new BigDecimal(50)) >= 0;
    }
}
