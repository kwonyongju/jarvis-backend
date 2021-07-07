package com.management.inventory.yjinventorymanagement.repository.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.Purchase;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PurchaseQueryDto {

    @JsonIgnore
    private Long purchaseId;
    private Person customer;
    private List<PurchaseItemQueryDto> purchaseItems;
    private Long totalPriceInCent;
    private LocalDateTime purchaseDate;

    public PurchaseQueryDto(Purchase purchase) {
        this.purchaseId = purchase.getId();
        this.customer = purchase.getPerson();
        this.purchaseItems = purchase.getPurchaseItems()
                .stream()
                .map(PurchaseItemQueryDto::new)
                .collect(Collectors.toList());
        this.totalPriceInCent = purchase.getTotalPriceInCent();
        this.purchaseDate = purchase.getPurchaseDate();
    }
}
