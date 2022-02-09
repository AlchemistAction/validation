package validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import validation.controller.Controller;
import validation.model.UserDtoRequest;
import validation.model.UserOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Controller.class)
@ComponentScan("validation")
public class ControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testAddUserFailNullName() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest(1, null, "Login");

        MvcResult result = mvc.perform(post("/api/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void testAddUserOnCreate() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest(null, "name", "Login");

        MvcResult result = mvc.perform(post("/api/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest(3, "name", "Login");

        MvcResult result = mvc.perform(post("/api/validation/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testAddUserFailEmptyName() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest(1, "", "Login");

        MvcResult result = mvc.perform(post("/api/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void testAddUserFailEmptyStringList() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest(Arrays.asList("book1", "", "book3"));

        MvcResult result = mvc.perform(post("/api/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void testAddUserFailOrderInvalid() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest("name", "Login", new UserOrder(-9, "sdf"));

        MvcResult result = mvc.perform(post("/api/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDtoRequest)))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

//    @Test
//    public void testAddUserFailResponse() throws Exception {
//        UserDtoRequest userDtoRequest = new UserDtoRequest("name", "Login", new UserOrder(9, "sdf"));
//
//        MvcResult result = mvc.perform(post("/api/validation/response")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(userDtoRequest)))
//                .andReturn();
//        assertEquals(400, result.getResponse().getStatus());
//    }
}
