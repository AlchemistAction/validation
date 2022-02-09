package validation;

import org.junit.jupiter.api.Test;
import validation.model.UserDtoRequest;
import validation.model.UserOrder;
import validation.service.UserService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    @Test()
    public void testAddUserFailCustomValidation() {
        UserDtoRequest userDtoRequest = new UserDtoRequest("", "Login", new UserOrder(9, "sdf"));
        UserService userService = new UserService();

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userService.addUser(userDtoRequest));

        assertTrue(thrown.getMessage().contains("Name must not be blank"));
    }

    @Test()
    public void testAddUserFailCustomClassLevelValidation() {
        UserDtoRequest userDtoRequest = new UserDtoRequest("name", "Login",
                new UserOrder(1, "ticket", BigDecimal.valueOf(22), BigDecimal.valueOf(2)));
        UserService userService = new UserService();

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userService.addUser(userDtoRequest));

        assertTrue(thrown.getMessage().contains("total price must be 50 or greater for online order. Found: 44"));
    }
}
