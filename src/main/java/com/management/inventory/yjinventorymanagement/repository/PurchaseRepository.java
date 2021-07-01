package com.management.inventory.yjinventorymanagement.repository;

import com.management.inventory.yjinventorymanagement.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
