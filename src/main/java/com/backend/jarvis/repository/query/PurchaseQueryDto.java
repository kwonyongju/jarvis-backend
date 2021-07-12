package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.Person;
import com.backend.jarvis.domain.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
