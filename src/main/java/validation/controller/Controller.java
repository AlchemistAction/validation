package validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import validation.model.UserDtoRequest;
import validation.model.UserDtoResponse;
import validation.service.UserService;
import validation.validator.OnCreate;
import validation.validator.OnUpdate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/validation")
@Validated
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Validated(OnCreate.class)
    public UserDtoResponse addUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        System.out.println(userDtoRequest.toString());
        userDtoRequest.setId(3);
        return new UserDtoResponse(userDtoRequest.getId(), userDtoRequest.getName());
    }

    @PostMapping(value = {"/update"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Validated(OnUpdate.class)
    public UserDtoResponse updateUser(@RequestBody UserDtoRequest userDtoRequest) {
        System.out.println(userDtoRequest.toString());
        return new UserDtoResponse(userDtoRequest.getId(), userDtoRequest.getName());
    }

    @PostMapping(value = {"/response"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @Valid @ResponseBody UserDtoResponse addUserResponseValidation(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        System.out.println(userDtoRequest.toString());
        return new UserDtoResponse(userDtoRequest.getId(), null);
    }

    @PostMapping(value = {"/custom"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDtoResponse addUserCustom(@RequestBody UserDtoRequest userDtoRequest) {
        return userService.addUser(userDtoRequest);
    }
}
