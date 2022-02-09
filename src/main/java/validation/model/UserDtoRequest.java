package validation.model;

import validation.validator.Login;
import validation.validator.OnCreate;
import validation.validator.OnUpdate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

public class UserDtoRequest {
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    private String name;

    @Login(field = "Login")
    private String login;

    @Valid
    private UserOrder order;

    private List<@NotBlank String> books;

    public UserDtoRequest() {
    }

    public UserDtoRequest(Integer id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

    public UserDtoRequest(List<@NotBlank String> books) {
        this.books = books;
    }

    public UserDtoRequest(String name,String login, @Valid UserOrder order) {
        this.name = name;
        this.login = login;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoRequest that = (UserDtoRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(login, that.login) && Objects.equals(order, that.order) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, order, books);
    }
}
