package com.management.inventory.yjinventorymanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "purchase_item")
public class PurchaseItem {

    @Id
    @GeneratedValue
    @Column(name = "purchase_item_id")
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "purchase_id")
    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @Setter
    private Purchase purchase;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    private Item item;

    @Setter
    private Long totalPriceInCent;
    private int quantity;

    protected PurchaseItem() {

    }

    public PurchaseItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public static PurchaseItem createPurchaseItem(Item item, int quantity) {
        PurchaseItem purchaseItem = new PurchaseItem(item, quantity);
        purchaseItem.setTotalPriceInCent(item.getPriceInCent() * quantity);

        return purchaseItem;
    }
}
