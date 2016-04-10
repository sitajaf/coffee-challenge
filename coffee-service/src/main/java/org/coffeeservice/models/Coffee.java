package org.coffeeservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor //needed by jackson
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Coffee {
    private String name;
    @JsonProperty("order_path")
    private String orderPath;
    private Double price;
    @JsonProperty("caffeine_level")
    private int caffeineLevel;
    @JsonProperty("milk_ratio")
    private double milkRatio;
}
