package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.IngredientFactory;
import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.IngredientRepository;
import com.management.inventory.yjinventorymanagement.repository.query.OrderQueryDto;
import com.management.inventory.yjinventorymanagement.repository.query.OrderQueryRepository;
import com.management.inventory.yjinventorymanagement.service.OrderService;
import com.management.inventory.yjinventorymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {

    private final OrderQueryRepository orderQueryRepository;
    private final IngredientRepository ingredientRepository;
    private final PersonService personService;
    private final OrderService orderService;


    @GetMapping("/api/v1/orders")
    public GetOrderResponse getOrders(@RequestParam Long id) {
        List<Order> orders = orderQueryRepository.findAllByPersonId(id);

        List<OrderQueryDto> result = orders.stream()
                .map(OrderQueryDto::new).distinct().collect(Collectors.toList());

        return new GetOrderResponse(result.size(), result);
    }

    @PostMapping("api/v1/order")
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        Person person = personService.findById(Long.parseLong(request.getPersonId()));


        List<OrderIngredient> orderIngredients = request.getOrderIngredients()
                .stream()
                .map(oi -> {
                    // Create ingredient if it is a new ingredient
                    Ingredient ingredient = ingredientRepository.findByName(oi.getIngredient()) == null
                            ? IngredientFactory.createIngredient(oi.getIngredient())
                            : ingredientRepository.findByName(oi.getIngredient());

                    return OrderIngredient.createOrderIngredient
                            (ingredient, oi.getQuantity());
                })
                .collect(Collectors.toList());

        Long orderId = orderService.order(person.getId(), orderIngredients);

        return new CreateOrderResponse(orderId);
    }

    @Data
    static class CreateOrderRequest {
        @NotEmpty
        private String personId;
        @NotEmpty
        private List<IngredientRequest> orderIngredients;
    }

    @Data
    static class IngredientRequest {
        private String ingredient;
        private int quantity;
    }

    @Data
    @AllArgsConstructor
    static class GetOrderResponse<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class CreateOrderResponse {
        private Long id;
    }
}
