package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
}
