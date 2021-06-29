package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long Id;

//    private List<OrderIngredient> order

    private LocalDateTime orderDate;
}
