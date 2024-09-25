package com.system.pos_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.entity.ItemCategory;
import com.system.pos_system.service.ItemCategoryService;

@RestController
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/category")
    public ResponseEntity<List<ItemCategory>> getAllItemCategories() {
        return ResponseEntity.status(200).body(itemCategoryService.getAllItemCategories());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ItemCategory> getItemCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(itemCategoryService.getItemCategoryById(id));
    }

    @PostMapping("/category")
    public ResponseEntity<ItemCategory> createItemCategory(@RequestBody ItemCategory itemCategory) {
        return ResponseEntity.status(201).body(itemCategoryService.createItemCategory(itemCategory));
    }
}
