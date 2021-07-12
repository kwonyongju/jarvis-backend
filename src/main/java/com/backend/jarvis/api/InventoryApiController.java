package com.backend.jarvis.api;

import com.backend.jarvis.constant.IngredientCatalog;
import com.backend.jarvis.constant.ItemCatalog;
import com.backend.jarvis.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class InventoryApiController {

    private final InventoryService inventoryService;

    @GetMapping("/api/v1/inventory")
    public GetResponse getInventory() {
        List<InventoryDto> inventoryList = inventoryService.findAll()
                .stream()
                .map(i -> {
                    Long priceInCent = IngredientCatalog
                            .valueOf(ItemCatalog.convertToEnumCase(i.getIngredientName()))
                            .getPriceInCent();
                    return new InventoryDto(i.getIngredientName(), i.getStockQuantity(), priceInCent);
                })
                .collect(Collectors.toList());

        return new GetResponse(inventoryList.size(), inventoryList);
    }

    @Data
    @AllArgsConstructor
    static class InventoryDto {
        private String ingredientName;
        private int stockQuantity;
        private Long priceInCent;
    }
}
