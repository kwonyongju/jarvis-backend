package com.management.inventory.yjinventorymanagement;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;
import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.service.MenuService;
import com.management.inventory.yjinventorymanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DBInitialization {

    private final InitializationService initializationService;

    @PostConstruct
    public void init() throws Exception {
        initializationService.initializeDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitializationService {

        private final EntityManager em;
        private final MenuService menuService;
        private final OrderService orderService;

        public void initializeDB() throws Exception {
            Person customer = new Person("customer", "Kwon", "customer@gmail.com");
            Person manager = new Person("manager", "Kwon", "manager@gmail.com");
            em.persist(customer);
            em.persist(manager);

            // Create ingredients
            Ingredient bacon = new Bacon("Bacon", 39L);
            Ingredient bun = new Bun("Bun", 29L);
            Ingredient cheese = new Cheese("Cheese", 89L);
            Ingredient lettuce = new Lettuce("Lettuce", 10L);
            Ingredient patty = new Patty("Patty", 599L);
            Ingredient tomato = new Tomato("Tomato", 35L);

            List<Ingredient> ingredientList = new ArrayList<>(List.of(bacon, bun, cheese, lettuce, patty, tomato));

            List<OrderIngredient> orderIngredients = new ArrayList<>();

            // Stock ingredients
            Random randomGenerator = new Random();
            for (Ingredient i : ingredientList) {
                int qty = randomGenerator.nextInt(20) + 10;

                OrderIngredient orderIngredient = OrderIngredient.createOrderIngredient(i, qty);
                orderIngredients.add(orderIngredient);

                log.info("{} of {} is stored in inventory", qty, i.getName());
            }

            // Add menu
            ItemCatalog[] itemCatalogs = ItemCatalog.values();
            for (ItemCatalog ic : itemCatalogs) {
                menuService.add(new Menu(ic.getFormattedName(), ic.getDescription(), ic.getPriceInCent()));
            }

            orderService.order(manager.getId(), orderIngredients);
        }
    }
}
