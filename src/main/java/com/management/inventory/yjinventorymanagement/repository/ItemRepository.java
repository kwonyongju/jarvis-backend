package com.management.inventory.yjinventorymanagement.repository;

import com.management.inventory.yjinventorymanagement.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
