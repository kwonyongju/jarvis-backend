package com.backend.jarvis.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Menu {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long Id;

    private String itemName;
    private String itemDescription;

    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    private Long priceInCent;

    public Menu(String itemName, String itemDescription, List<String> ingredients, Long priceInCent) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.ingredients = ingredients;
        this.priceInCent = priceInCent;
    }

    protected Menu() {
    }
}
