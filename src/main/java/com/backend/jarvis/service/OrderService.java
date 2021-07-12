package com.backend.jarvis.service;

import com.backend.jarvis.domain.Ingredient.Ingredient;
import com.backend.jarvis.domain.Ingredient.IngredientFactory;
import com.backend.jarvis.domain.Order;
import com.backend.jarvis.domain.OrderIngredient;
import com.backend.jarvis.domain.Person;
import com.backend.jarvis.repository.IngredientRepository;
import com.backend.jarvis.repository.OrderRepository;
import com.backend.jarvis.repository.PersonRepository;
import com.backend.jarvis.repository.query.OrderQueryRepository;
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
