package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
}
