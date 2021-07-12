package com.backend.jarvis;

import com.backend.jarvis.constant.ItemCatalog;
import com.backend.jarvis.domain.Person;
import com.backend.jarvis.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DBInitialization {

    private final InitializationService initializationService;

    @PostConstruct
    public void init() throws Exception {
//        initializationService.initializeDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitializationService {

        private final PersonService personService;
        private final MenuService menuService;
        private final IngredientService ingredientService;
        private final OrderService orderService;
        private final PurchaseService purchaseService;

        public void initializeDB() throws Exception {
            Person customer = new Person("Schwarzenegger", "Arnold (Customer)", "customer@gmail.com");
            Person customer2 = new Person("Mayweather", "Floyd (Customer)", "customer2@gmail.com");
            Person manager = new Person("Johnson", "Dwayne (Manager)", "manager@gmail.com");
            personService.register(customer);
            personService.register(customer2);
            personService.register(manager);

            // Create ingredients
            ingredientService.add("Bun");
            ingredientService.add("Cheese");
            ingredientService.add("Bacon");
            ingredientService.add("Lettuce");
            ingredientService.add("Patty");
            ingredientService.add("Tomato");

            // Add menu
            ItemCatalog[] itemCatalogs = ItemCatalog.values();
            for (ItemCatalog ic : itemCatalogs) {
                menuService.add(ic.getFormattedName(), ic.getDescription(), ic.getIngredientsList(), ic.getPriceInCent());
            }

            Map<String, Integer> ingredients = new HashMap<>();
            ingredients.put("Bun", 33);
            ingredients.put("Cheese", 48);
            ingredients.put("Lettuce", 28);
            ingredients.put("Patty", 87);

            orderService.order(manager.getId(), ingredients);

            Map<String, Integer> items = new HashMap<>();
            items.put("Big mac", 2);
            items.put("Double big mac", 1);

            // Mock purchase
            purchaseService.purchase(customer2.getId(), items);
        }
    }
}
