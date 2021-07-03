package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

//    public Long addStock(Person person, Ingredient ingredient, int count) {
//        Order order = new Order(person, ingredient, 100, LocalDateTime.now());
//    }
}
