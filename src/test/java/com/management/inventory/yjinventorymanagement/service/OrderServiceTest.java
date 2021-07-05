package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    PersonService personService;

    private Person customer;

    @BeforeAll
    void initialize() {
        customer = new Person("Yongju", "Kwon", "yongjuKwon@gmail.com");
        personService.register(customer);
    }

    @Test
    void orderTest() throws Exception {
        Map<String, Integer> ingredients = new HashMap<>();
        ingredients.put("Bun", 33);
        orderService.order(customer.getId(), ingredients);

        Assertions.assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(33);
    }
}