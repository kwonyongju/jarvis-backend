package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventoryService inventoryService;

    public Item createItem(String itemName) {
        String enumName = ItemCatalog.convertToEnumCase(itemName);
        ItemCatalog itemCatalog = ItemCatalog.valueOf(enumName);

        for (String ingredientName : itemCatalog.getIngredients()) {
            inventoryService.removeStock(ingredientName);
        }

        Item item = new Item(itemName, itemCatalog.getPriceInCent(), Arrays.asList(itemCatalog.getIngredients()));
        itemRepository.save(item);

        return item;
    }
}
