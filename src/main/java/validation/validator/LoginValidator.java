package validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<Login, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ((s != null) && Pattern.matches("[а-яА-Яa-zA-Z0-9]+", s) && (s.length() < 10));
    }
}
