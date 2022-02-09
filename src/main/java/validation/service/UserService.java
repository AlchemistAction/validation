package validation.service;

import org.springframework.stereotype.Service;
import validation.model.UserDtoRequest;
import validation.model.UserDtoResponse;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserService {

    public UserDtoResponse addUser(UserDtoRequest userDtoRequest) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDtoRequest>> violations = validator.validate(userDtoRequest);
        if (!violations.isEmpty()) {
            violations.forEach(v -> System.out.println(v.getMessage()));
            ConstraintViolation<UserDtoRequest> userConstraintViolation = violations.stream().findFirst().orElseThrow(RuntimeException::new);
            throw new RuntimeException(userConstraintViolation.getMessage());
        }
        return new UserDtoResponse(userDtoRequest.getId(), userDtoRequest.getName());
    }
}
