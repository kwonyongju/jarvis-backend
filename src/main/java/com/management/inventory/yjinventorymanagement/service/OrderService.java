package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;

    public Long order(Long managerId, Map<Ingredient, Integer> ingredients) {
        Person manager = personRepository.getById(managerId);

        List<OrderIngredient> orderIngredients = new ArrayList<>();
        for (Ingredient i : ingredients.keySet()) {
            int quantity = ingredients.get(i);
            orderIngredients.add(OrderIngredient.createOrderIngredient(i, quantity));
            // Add stock to inventory
            inventoryService.addStock(i.getName(), quantity);
        }

        Order order = Order.createOrder(manager, orderIngredients);
        orderRepository.save(order);

        return order.getId();
    }
}
