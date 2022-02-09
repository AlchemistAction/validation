package validation.model;

import validation.validator.ValidOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@ValidOrder
public class UserOrder {
    @Positive
    private int id;
    @NotBlank
    private String ticket;

    private BigDecimal amount;
    private BigDecimal quantity;

    public UserOrder() {
    }

    public UserOrder(int id, String ticket) {
        this.id = id;
        this.ticket = ticket;
    }

    public UserOrder(int id, String ticket, BigDecimal amount, BigDecimal quantity) {
        this.id = id;
        this.ticket = ticket;
        this.amount = amount;
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return (amount != null && quantity != null ? amount.multiply(quantity) : BigDecimal.ZERO);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id && Objects.equals(ticket, userOrder.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket);
    }
}
