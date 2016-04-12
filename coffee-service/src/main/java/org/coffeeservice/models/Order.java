package org.coffeeservice.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @NotNull
    String size;

    @NotNull
    List<String> extras;

    int pickUpTime;

}
