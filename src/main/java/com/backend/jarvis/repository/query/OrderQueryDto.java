package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.Order;
import com.backend.jarvis.domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

    @JsonIgnore
    private Long orderId;
    private Person person;
    private LocalDateTime orderDate;
    private List<OrderIngredientQueryDto> orderIngredients;

    public OrderQueryDto(Order order) {
        this.orderId = order.getId();
        this.person = order.getPerson();
        this.orderDate = order.getOrderDate();
        this.orderIngredients = order.getOrderIngredients()
                .stream()
                .map(OrderIngredientQueryDto::new)
                .collect(Collectors.toList());
    }
}
