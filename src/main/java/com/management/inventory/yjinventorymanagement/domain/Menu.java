package com.management.inventory.yjinventorymanagement.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Getter
public class Menu {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long Id;

    private String itemName;
    private String itemDescription;

    private Long priceInCent;

    public Menu(String itemName, String itemDescription, Long priceInCent) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.priceInCent = priceInCent;
    }

    protected Menu() {
    }
}
