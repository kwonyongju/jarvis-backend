package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
}
