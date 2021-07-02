package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Long add(Ingredient ingredient) {
        Ingredient ingredientSaved = ingredientRepository.save(ingredient);

        return ingredientSaved.getId();
    }

    public void add(Ingredient... ingredients) {
        ingredientRepository.saveAll(Arrays.asList(ingredients));
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
