package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventoryService inventoryService;
    private final MenuService menuService;

    public Item createItem(String itemName, int quantity) {
        Menu menu = menuService.getMenuByItemName(itemName);
        List<String> ingredients = menu.getIngredients();

        for (int i = 0; i < quantity; ++i)
            for (String ingredient : ingredients)
                inventoryService.removeStock(ingredient);


        Item item = new Item(itemName, menu.getPriceInCent());
        itemRepository.save(item);
        return item;
    }
}
