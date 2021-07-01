package com.management.inventory.yjinventorymanagement;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;
import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.service.IngredientService;
import com.management.inventory.yjinventorymanagement.service.InventoryService;
import com.management.inventory.yjinventorymanagement.service.MenuService;
import com.management.inventory.yjinventorymanagement.service.PurchaseService;
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
    public void init() {
        initializationService.initializeDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitializationService {

        private final EntityManager em;
        private final IngredientService ingredientService;
        private final InventoryService inventoryService;
        private final MenuService menuService;
        private final PurchaseService purchaseService;

        public void initializeDB() {
            Person customer = new Person("Yongju", "Kwon", "yongjuKwon@gmail.com");
            em.persist(customer);

            // Create ingredients
            Ingredient bacon = new Bacon("bacon", 39L);
            Ingredient bun = new Bun("bun", 29L);
            Ingredient cheese = new Cheese("cheese", 89L);
            Ingredient lettuce = new Lettuce("lettuce", 10L);
            Ingredient patty = new Patty("patty", 599L);
            Ingredient tomato = new Tomato("tomato", 35L);

            List<Ingredient> ingredientList = new ArrayList<>(List.of(bacon, bun, cheese, lettuce, patty, tomato));

            // Stock ingredients
            Random randomGenerator = new Random();
            for (Ingredient i: ingredientList) {
                ingredientService.add(i);
                int qty = randomGenerator.nextInt(20) + 10;
                inventoryService.addStock(i.getName(), qty);
                log.info("{} of {} is stored in inventory", qty, i.getName());
            }

            // Add menu
            ItemCatalog[] itemCatalogs = ItemCatalog.values();
            for (ItemCatalog ic: itemCatalogs) {
                menuService.add(new Menu(ic.getFormattedName(), ic.getDescription(), ic.getPriceInCent()));
            }

            // Create purchase
            purchaseService.purchase(customer, "hamburger", "cheese burger", "big mac");
        }
    }
}
