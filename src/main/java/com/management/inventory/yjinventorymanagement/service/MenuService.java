package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Long add(Menu menu) {
        Menu menuSaved = menuRepository.save(menu);

        return menuSaved.getId();
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }
}
