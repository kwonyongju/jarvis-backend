package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
}
