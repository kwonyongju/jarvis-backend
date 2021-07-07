package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import com.management.inventory.yjinventorymanagement.repository.query.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public Long order(Long managerId, List<OrderIngredient> orderIngredients) {
        Optional<Person> manager = personRepository.findById(managerId);
        if (!manager.isPresent())
            throw new IllegalArgumentException("No such person!");

        for (OrderIngredient oi : orderIngredients)
            inventoryService.addStock(oi.getIngredient().getName(), oi.getQuantity());

        Order order = Order.createOrder(manager.get(), orderIngredients);
        orderRepository.save(order);

        return order.getId();
    }

    public List<Order> findAll() {
        return orderQueryRepository.findAll();
    }

    public List<Order> findAllByPersonId(Long id) {
        return orderQueryRepository.findAllByPersonId(id);
    }
}
