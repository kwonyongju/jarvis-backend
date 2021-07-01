package com.management.inventory.yjinventorymanagement.repository;

import com.management.inventory.yjinventorymanagement.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByIngredientName(String ingredientName);
}
