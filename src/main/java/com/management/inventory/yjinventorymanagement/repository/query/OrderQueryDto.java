package com.management.inventory.yjinventorymanagement.repository.query;

import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

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
