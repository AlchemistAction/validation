package validation.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserDtoResponse {

    private int id;
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    private String name;

    public UserDtoResponse() {
    }

    public UserDtoResponse(int id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoResponse that = (UserDtoResponse) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
