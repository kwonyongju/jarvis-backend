package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.Purchase;
import com.management.inventory.yjinventorymanagement.domain.PurchaseItem;
import com.management.inventory.yjinventorymanagement.repository.query.PurchaseQueryDto;
import com.management.inventory.yjinventorymanagement.service.ItemService;
import com.management.inventory.yjinventorymanagement.service.PersonService;
import com.management.inventory.yjinventorymanagement.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PurchaseApiController {

    private final ItemService itemService;
    private final PurchaseService purchaseService;
    private final PersonService personService;

    @GetMapping("/api/v1/purchase")
    public GetResponse getPurchases() {
        List<Purchase> purchases = purchaseService.findAll();
        List<PurchaseQueryDto> result = convertPurchaseToDto(purchases);

        return new GetResponse(result.size(), result);
    }

    @GetMapping("/api/v1/purchase/{id}")
    public GetResponse getPurchases(@PathVariable("id") Long id) {
        List<Purchase> purchases = purchaseService.findAllByPersonId(id);
        List<PurchaseQueryDto> result = convertPurchaseToDto(purchases);

        return new GetResponse(result.size(), result);
    }

    @PostMapping("/api/v1/purchase")
    public CreatePurchaseResponse createPurchase(@RequestBody @Valid CreatePurchaseRequest request) {
        Person customer = personService.findById(Long.parseLong(request.getPersonId()));

        List<PurchaseItem> purchaseItems = request.getItems()
                .stream()
                .map(i -> {
                    Item item = itemService.createItem(i.getItemName());

                    return PurchaseItem.createPurchaseItem(item, i.getQuantity());
                })
                .collect(Collectors.toList());

        Long purchaseId = purchaseService.purchase(customer.getId(), purchaseItems);

        return new CreatePurchaseResponse(purchaseId);
    }

    private List<PurchaseQueryDto> convertPurchaseToDto(List<Purchase> purchases) {
        return purchases
                .stream()
                .map(PurchaseQueryDto::new)
                .collect(Collectors.toList());
    }

    @Data
    static class CreatePurchaseRequest {
        @NotEmpty
        private String personId;
        @NotEmpty
        private List<PurchaseItemRequest> items;
    }

    @Data
    static class PurchaseItemRequest {
        private String itemName;
        private int quantity;
    }

    @Data
    @AllArgsConstructor
    static class CreatePurchaseResponse {
        private Long id;
    }
}
