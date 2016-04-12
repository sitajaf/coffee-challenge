package org.coffeeservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OrderNote {
    String order;
    @JsonProperty("wait_time")
    float waitTime;

    public OrderNote(String order, int waitTime) {
        this.order = order;
        this.waitTime = waitTime;
    }
}
