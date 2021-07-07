package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.Purchase;
import com.management.inventory.yjinventorymanagement.domain.PurchaseItem;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import com.management.inventory.yjinventorymanagement.repository.PurchaseRepository;
import com.management.inventory.yjinventorymanagement.repository.query.PurchaseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseQueryRepository purchaseQueryRepository;
    private final PurchaseRepository purchaseRepository;
    private final PersonRepository personRepository;
    private final ItemService itemService;

    public Long purchase(Long customerId, Map<String, Integer> items) {
        Optional<Person> customer = personRepository.findById(customerId);
        if (customer.isEmpty())
            throw new IllegalArgumentException("No such customer!");

        List<PurchaseItem> purchaseItems = items.keySet()
                .stream()
                .map(itemName -> {
                    int quantity = items.get(itemName);
                    Item item = itemService.createItem(itemName, quantity);

                    return PurchaseItem.createPurchaseItem(item, quantity);
                })
                .collect(Collectors.toList());

        Purchase purchase = Purchase.purchase(customer.get(), purchaseItems);
        purchaseRepository.save(purchase);

        return purchase.getId();
    }

    public List<Purchase> findAll() {
        return purchaseQueryRepository.findAll();
    }

    public List<Purchase> findAllByPersonId(Long id) {
        return purchaseQueryRepository.findAllByPersonId(id);
    }

}
