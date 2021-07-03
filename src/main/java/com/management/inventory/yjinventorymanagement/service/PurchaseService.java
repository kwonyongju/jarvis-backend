package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.Purchase;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import com.management.inventory.yjinventorymanagement.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final ItemService itemService;

    private final PurchaseRepository purchaseRepository;
    private final PersonRepository personRepository;

    public Long purchase(Person person, String... itemNames) {
        Person customer = personRepository.findByEmail(person.getEmail()).get();
        List<Item> items = new ArrayList<>();

        // order
        for (String itemName : itemNames)
            items.add(itemService.createItem(itemName));

        Purchase purchase = Purchase.orderItems(customer, items);

        purchaseRepository.save(purchase);
        return purchase.getId();
    }

}
