package com.management.inventory.yjinventorymanagement;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;
import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DBInitialization {

    private final InitializationService initializationService;

    @PostConstruct
    public void init() {
        initializationService.initializeDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitializationService {

        private final EntityManager em;

        public void initializeDB() {
            Inventory inventory = new Inventory();

            // Create ingredients
            Ingredient bacon = new Bacon("bacon", 49L);
            Ingredient bun = new Bun("bun", 99L);
            Ingredient cheese = new Cheese("cheese", 199L);
            Ingredient lettuce = new Lettuce("lettuce", 49L);
            Ingredient patty = new Patty("patty", 599L);
            Ingredient tomato = new Tomato("tomato", 199L);

            List<Ingredient> ingredientList = new ArrayList<>(List.of(bacon, bun, cheese, lettuce, patty, tomato));

            // Stock ingredients
            Random randomGenerator = new Random();
            for (Ingredient i: ingredientList) {
                em.persist(i);
                int qty = randomGenerator.nextInt(20) + 10;
                inventory.addStock(i, qty);
            }

            em.persist(inventory);

        }
    }
}
