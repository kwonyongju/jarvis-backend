package com.backend.jarvis.repository;

import com.backend.jarvis.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByItemName(String itemName);
}
