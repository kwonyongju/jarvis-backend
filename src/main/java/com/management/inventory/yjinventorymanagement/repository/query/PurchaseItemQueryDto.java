package com.management.inventory.yjinventorymanagement.repository.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.inventory.yjinventorymanagement.domain.PurchaseItem;
import lombok.Data;

@Data
public class PurchaseItemQueryDto {

    @JsonIgnore
    private Long purchaseItemId;
    private String itemName;
    private Long totalPriceInCent;
    private int quantity;

    public PurchaseItemQueryDto(PurchaseItem purchaseItem) {
        this.purchaseItemId = purchaseItem.getId();
        this.itemName = purchaseItem.getItem().getName();
        this.totalPriceInCent = purchaseItem.getTotalPriceInCent();
        this.quantity = purchaseItem.getQuantity();
    }
}
