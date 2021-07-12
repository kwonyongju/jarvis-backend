package com.backend.jarvis.repository;

import com.backend.jarvis.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
