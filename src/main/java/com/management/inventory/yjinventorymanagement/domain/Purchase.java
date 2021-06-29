package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "purchase")
    private List<Item> items = new ArrayList<>();

    private Long totalPriceInCent;
    private LocalDateTime purchaseDate;
}
