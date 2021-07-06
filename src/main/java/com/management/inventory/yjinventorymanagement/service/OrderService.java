package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;

    public Long order(Long managerId, List<OrderIngredient> orderIngredients) {
        Person manager = personRepository.getById(managerId);

        for (OrderIngredient oi : orderIngredients)
            inventoryService.addStock(oi.getIngredient().getName(), oi.getQuantity());

        Order order = Order.createOrder(manager, orderIngredients);
        orderRepository.save(order);

        return order.getId();
    }
}
