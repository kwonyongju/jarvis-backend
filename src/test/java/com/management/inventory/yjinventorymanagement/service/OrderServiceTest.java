package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    PersonService personService;

    private Person customer;
    private Person manager;

    @BeforeAll
    void initialize() {
        customer = new Person("customer", "Kwon", "customer2@gmail.com");
        manager = new Person("manager", "Kwon", "manager2@gmail.com");

        personService.register(customer);
        personService.register(manager);
    }

    @Test
    @Order(1)
    @DisplayName("Check if ingredients properly get stocked after orders")
    void orderTest() {
        Map<String, Integer> ingredients = new HashMap<>();
        ingredients.put("Bun", 23);
        ingredients.put("Patty", 38);

        orderService.order(customer.getId(), ingredients);

        assertAll("Assert correct quantity of ingredients are stocked",
                () -> assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(23),
                () -> assertThat(inventoryService.getStockQuantity("Patty")).isSameAs(38)
        );
    }

    @Test
    @Order(2)
    @DisplayName("Check if service properly finds orders")
    void getOrderTest() {
        assertThat(orderService.findAll().size()).isSameAs(2);
    }

    @Test
    @Order(3)
    @DisplayName("Check if service properly filters orders by person id")
    void getOrderByPersonIdTest() {
        assertAll("Check if service properly filters orders by person id",
                () -> assertThat(orderService.findAllByPersonId(customer.getId()).size()).isSameAs(1),
                () -> assertThat(orderService.findAllByPersonId(manager.getId()).size()).isSameAs(0)
        );
    }

}