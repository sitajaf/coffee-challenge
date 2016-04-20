package org.coffeeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.coffeeservice.enums.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Order {
    @NotNull
    String size;

    @NotNull
    List<String> extras;

    int pickUpTime;

    @JsonIgnore
    OrderStatus status;

    public Order(String size, List<String> extras, int pickUpTime) {
        this.size = size;
        this.extras = extras;
        this.pickUpTime = pickUpTime;
    }
}
