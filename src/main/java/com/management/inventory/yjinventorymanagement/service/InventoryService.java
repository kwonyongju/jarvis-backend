package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.exception.IngredientNotExistException;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;
import com.management.inventory.yjinventorymanagement.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void addStock(String ingredientName, int qty) {
        Inventory inventoryFound =
                inventoryRepository.findByIngredientName(ingredientName) == null
                        ? new Inventory(ingredientName, qty)
                        : inventoryRepository.findByIngredientName(ingredientName);

        inventoryFound.setStockQuantity(inventoryFound.getStockQuantity() + qty);

        inventoryRepository.save(inventoryFound);
    }

    public void removeStock(String ingredientName) {
        Inventory inventoryFound = inventoryRepository.findByIngredientName(ingredientName);

        inventoryFound.setStockQuantity(inventoryFound.getStockQuantity() - 1);

        if (inventoryFound.getStockQuantity() < 0)
            throw new NotEnoughStockException(ingredientName + " is out of stock!");

        inventoryRepository.save(inventoryFound);
    }


}
