package com.system.pos_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.dto.ItemCategoryReqDTO;
import com.system.pos_system.entity.ItemCategory;
import com.system.pos_system.service.ItemCategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping
    public ResponseEntity<List<ItemCategory>> getAllItemCategories() {
        return ResponseEntity.status(200).body(itemCategoryService.getAllItemCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemCategoryById(@PathVariable Long id) {
        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(id);
        if (itemCategory == null) {
            return ResponseEntity.status(404).body("Item Category Not Found!");
        }
        return ResponseEntity.status(200).body(itemCategory);
    }

    @PostMapping
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategory itemCategory) {
        if (itemCategory.getName() == "" || itemCategory.getName() == null) {
            return ResponseEntity.status(400).body("Category Name Required!");
        }
        return ResponseEntity.status(201).body(itemCategoryService.createItemCategory(itemCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItemCategory(@PathVariable Long id,
            @RequestBody ItemCategoryReqDTO itemCategoryReqDTO) {
        if (itemCategoryReqDTO.getName() == null || itemCategoryReqDTO.getName() == "") {
            return ResponseEntity.status(422).body("Item Category Name Required!");
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(itemCategoryReqDTO.getName());
        itemCategoryService.updateItemCategory(id, itemCategory);
        return ResponseEntity.status(201).body("Updated Item Category!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemCategory(@PathVariable Long id) {
        if (itemCategoryService.getItemCategoryById(id) == null) {
            return ResponseEntity.status(404).body("Item Category Not Found!");
        }
        return ResponseEntity.status(201).body("Deleted Item Category!");
    }
}
