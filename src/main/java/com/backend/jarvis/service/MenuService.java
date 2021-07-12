package com.backend.jarvis.service;

import com.backend.jarvis.domain.Menu;
import com.backend.jarvis.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Long add(String itemName, String itemDescription, List<String> ingredients, Long priceInCent) {
        Menu menuSaved = menuRepository
                .save(new Menu(itemName, itemDescription, ingredients, priceInCent));

        return menuSaved.getId();
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    public Menu getMenuByItemName(String itemName) {
        return menuRepository.findByItemName(itemName);
    }
}
