package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.domain.Order;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.repository.IngredientRepository;
import com.management.inventory.yjinventorymanagement.repository.query.OrderQueryDto;
import com.management.inventory.yjinventorymanagement.service.OrderService;
import com.management.inventory.yjinventorymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {

    private final IngredientRepository ingredientRepository;
    private final PersonService personService;
    private final OrderService orderService;

    @GetMapping({"/api/v1/orders", "/api/v1/orders/{id}"})
    public GetResponse getOrders(@PathVariable(required = false) Long id) {
        List<Order> orders =
                id == null
                        ? orderService.findAll()
                        : orderService.findAllByPersonId(id);

        List<OrderQueryDto> result = orders.stream()
                .map(OrderQueryDto::new).distinct().collect(Collectors.toList());

        return new GetResponse(result.size(), result);
    }

    @PostMapping("api/v1/order")
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        Person person = personService.findById(Long.parseLong(request.getPersonId()));

        Map<String, Integer> ingredients = new HashMap<>();
        for (IngredientRequest ir : request.getOrderIngredients())
            ingredients.put(ir.getIngredient(), ir.getQuantity());

        Long orderId = orderService.order(person.getId(), ingredients);
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
    static class CreateOrderResponse {
        private Long id;
    }
}
