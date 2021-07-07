package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventoryService inventoryService;

    public Item createItem(String itemName, int quantity) {
        String enumName = ItemCatalog.convertToEnumCase(itemName);
        ItemCatalog itemCatalog = ItemCatalog.valueOf(enumName);

        for (int i = 0; i < quantity; ++i)
            for (String ingredient : itemCatalog.getIngredientsList())
                inventoryService.removeStock(ingredient);


        Item item = new Item(itemName, itemCatalog.getPriceInCent());
        itemRepository.save(item);
        return item;
    }
}
