package com.management.inventory.yjinventorymanagement.api;

import com.management.inventory.yjinventorymanagement.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @GetMapping("/api/v1/menu")
    public GetResponse getMenu() {
        List<MenuDto> menuList = menuService.getAllMenu()
                .stream()
                .map(
                        m -> new MenuDto(
                                m.getItemName(),
                                m.getItemDescription(),
                                m.getIngredients().stream().distinct().collect(Collectors.toList()),
                                m.getPriceInCent())
                )
                .collect(Collectors.toList());

        return new GetResponse(menuList.size(), menuList);
    }

    @PostMapping("/api/v1/menu")
    public CreateMenuResponse addMenu(@RequestBody @Valid CreateMenuRequest request) {
        Long menuId = menuService.add(
                request.getItemName(),
                request.getItemDescription(),
                request.getIngredients(),
                Long.parseLong(request.getPriceInCent())
        );

        return new CreateMenuResponse(menuId);
    }


    @Data
    @AllArgsConstructor
    static class MenuDto {
        private String itemName;
        private String itemDescription;
        private List<String> ingredients;
        private Long priceInCent;
    }

    @Data
    static class CreateMenuRequest {
        @NotEmpty
        private String itemName;
        @NotEmpty
        private String itemDescription;
        @NotEmpty
        private List<String> ingredients;
        @NotEmpty
        private String priceInCent;
    }

    @Data
    @AllArgsConstructor
    static class CreateMenuResponse {
        private Long id;
    }
}
