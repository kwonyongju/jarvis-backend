package com.backend.jarvis.service;

import com.backend.jarvis.constant.ItemCatalog;
import com.backend.jarvis.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    void addMenu() {
        ItemCatalog[] itemCatalogs = ItemCatalog.values();
        for (ItemCatalog ic : itemCatalogs)
            menuService
                    .add(ic.getFormattedName(),
                            ic.getDescription(),
                            ic.getIngredientsList(), ic.getPriceInCent());

        List<Menu> menu = menuService.getAllMenu();

        Assertions.assertThat(menu.size()).isSameAs(itemCatalogs.length);
    }
}