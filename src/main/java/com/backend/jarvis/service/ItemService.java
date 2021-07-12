package com.backend.jarvis.service;

import com.backend.jarvis.domain.Item;
import com.backend.jarvis.domain.Menu;
import com.backend.jarvis.repository.ItemRepository;
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
        String capitalizedItemName = itemName.substring(0, 1).toUpperCase() + itemName.substring(1);
        Menu menu = menuService.getMenuByItemName(capitalizedItemName);
        List<String> ingredients = menu.getIngredients();

        for (int i = 0; i < quantity; ++i)
            for (String ingredient : ingredients)
                inventoryService.removeStock(ingredient);


        Item item = new Item(itemName, menu.getPriceInCent());
        itemRepository.save(item);
        return item;
    }
}
