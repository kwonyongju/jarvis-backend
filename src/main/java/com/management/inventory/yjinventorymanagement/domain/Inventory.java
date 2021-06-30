package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private Long Id;

    @ElementCollection
    @CollectionTable(name = "ingredient_stock", joinColumns = { @JoinColumn(name = "inventory_id")})
    @MapKeyColumn(name = "ingredient")
    @Column(name = "stock_quantity")
    private Map<String, Integer> stock = new HashMap<>();

    public void addStock(Ingredient ingredient, int qty) {
        String iName = ingredient.getName();
        if (stock.containsKey(iName))
            stock.put(iName, stock.get(iName) + qty);

        if (!stock.containsKey(iName))
            stock.put(iName, qty);
    }

    public void removeStock(String ingredientName) {
        int stockQuantity = stock.get(ingredientName);

        if (--stockQuantity < 0)
            throw new NotEnoughStockException(ingredientName + " is out of stock!");

        stock.put(ingredientName, stockQuantity);
    }
}
