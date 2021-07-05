package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventoryService inventoryService;

    public Item createItem(String itemName) throws Exception {
        String enumName = ItemCatalog.convertToEnumCase(itemName);
        ItemCatalog itemCatalog = ItemCatalog.valueOf(enumName);
        List<Ingredient> ingredients = new ArrayList<>();

        for (String ingredientName : itemCatalog.getIngredients()) {
            Ingredient ingredient = (Ingredient) Class.forName(ingredientName).getDeclaredConstructor().newInstance();
            ingredients.add(ingredient);

            inventoryService.removeStock(ingredientName);
        }

        Item item = new Item(itemName, itemCatalog.getPriceInCent(), ingredients);
        itemRepository.save(item);

        return item;
    }
}
