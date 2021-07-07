package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.Purchase;
import com.management.inventory.yjinventorymanagement.domain.PurchaseItem;
import com.management.inventory.yjinventorymanagement.repository.PersonRepository;
import com.management.inventory.yjinventorymanagement.repository.PurchaseRepository;
import com.management.inventory.yjinventorymanagement.repository.query.PurchaseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseQueryRepository purchaseQueryRepository;
    private final PurchaseRepository purchaseRepository;
    private final PersonRepository personRepository;

    public Long purchase(Long customerId, List<PurchaseItem> purchaseItems) {
        Optional<Person> customer = personRepository.findById(customerId);
        if (customer.isEmpty())
            throw new IllegalArgumentException("No such customer!");

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
