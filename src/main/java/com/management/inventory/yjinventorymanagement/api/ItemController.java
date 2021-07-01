package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
}
