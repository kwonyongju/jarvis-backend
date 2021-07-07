package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.IngredientFactory;
import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.IngredientRepository;
import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import com.management.inventory.yjinventorymanagement.repository.query.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final IngredientRepository ingredientRepository;
    private final InventoryService inventoryService;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public Long order(Long managerId, Map<String, Integer> ingredients) {
        Optional<Person> manager = personRepository.findById(managerId);
        if (!manager.isPresent())
            throw new IllegalArgumentException("No such person!");

        List<OrderIngredient> orderIngredients = ingredients.keySet()
                .stream()
                .map(ingredientName -> {
                    int quantity = ingredients.get(ingredientName);
                    // Create ingredient if it is a new ingredient
                    Ingredient ingredient = ingredientRepository.findByName(ingredientName) == null
                            ? IngredientFactory.createIngredient(ingredientName)
                            : ingredientRepository.findByName(ingredientName);

                    inventoryService.addStock(ingredientName, quantity);
                    return OrderIngredient.createOrderIngredient
                            (ingredient, quantity);
                })
                .collect(Collectors.toList());

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
