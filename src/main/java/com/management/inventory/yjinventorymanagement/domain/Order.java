package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
//    private List<OrderIngredient> orderIngredients = new ArrayList<>();

    private LocalDateTime orderDate;
}
