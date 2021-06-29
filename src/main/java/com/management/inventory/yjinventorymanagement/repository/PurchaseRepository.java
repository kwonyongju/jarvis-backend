package com.management.inventory.yjinventorymanagement.repository;

import com.management.inventory.yjinventorymanagement.domain.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseHistory, Long> {
}
