package com.management.inventory.yjinventorymanagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderIngredient> orderIngredients = new ArrayList<>();

    @Setter
    private LocalDateTime orderDate;

    protected Order() {
    }

    public Order(Person person, List<OrderIngredient> orderIngredients) {
        this.person = person;
        this.orderDate = LocalDateTime.now();

        for (OrderIngredient oi : orderIngredients)
            addOrderIngredient(oi);
    }

    public static Order createOrder(Person person, List<OrderIngredient> orderIngredients) {
        Order order = new Order(person, orderIngredients);
        order.orderDate = LocalDateTime.now();

        return order;
    }

    public void addOrderIngredient(OrderIngredient orderIngredient) {
        this.orderIngredients.add(orderIngredient);
    }
}
