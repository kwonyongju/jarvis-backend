package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;
import com.management.inventory.yjinventorymanagement.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void addStock(String ingredientName, int qty) {
        List<Inventory> inventory = inventoryRepository.findAll();

        Inventory inventoryFound = inventory.stream()
                .filter(i -> i.getIngredientName().equals(ingredientName))
                .findAny()
                .orElse(null);

        if (inventoryFound == null)
            inventoryFound = new Inventory(ingredientName, qty);
        else
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

    public int getStockQuantity(String ingredientName) {
        Inventory ingredientFound = inventoryRepository.findByIngredientName(ingredientName);

        return ingredientFound.getStockQuantity();
    }
}
