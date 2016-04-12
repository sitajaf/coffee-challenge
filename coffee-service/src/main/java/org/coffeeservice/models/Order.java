package org.coffeeservice.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Order {
    @NotNull
    String size;

    @NotNull
    List<String> extras;

    int pickUpTime;

}
