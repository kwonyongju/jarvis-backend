package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.*;

@Entity
public class Menu {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long Id;

    private String itemName;
    private String itemDescription;

    public Menu(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    protected Menu() {}
}
