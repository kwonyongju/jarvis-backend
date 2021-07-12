package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.PurchaseItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
