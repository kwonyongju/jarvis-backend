package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PurchaseServiceTest {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    ItemService itemService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    PersonService personService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    MenuService menuService;

    private Person customer;

    @BeforeAll
    void addMenu() {
        customer = new Person("Yongju", "Kwon", "yongjuKwon@gmail.com");
        personService.register(customer);

        ItemCatalog[] itemCatalogs = ItemCatalog.values();
        for (ItemCatalog ic : itemCatalogs)
            menuService.add(ic.getFormattedName(), ic.getDescription(), ic.getIngredientsList(), ic.getPriceInCent());
    }
}