package com.system.pos_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.dto.ItemReqDTO;
import com.system.pos_system.entity.Item;
import com.system.pos_system.entity.ItemCategory;
import com.system.pos_system.service.ItemCategoryService;
import com.system.pos_system.service.ItemService;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItem() {
        return ResponseEntity.status(200).body(itemService.getAllItem());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(itemService.getItemById(id));
    }

    @PostMapping("/item")
    public ResponseEntity<String> createItem(@RequestBody ItemReqDTO itemReqDTO) {
        if (itemReqDTO.getName() == null || itemReqDTO.getName() == "") {
            return ResponseEntity.status(422).body("Item Name Missing");
        }
        if (itemReqDTO.getPrice() == null || itemReqDTO.getPrice() <= 0) {
            return ResponseEntity.status(422).body("Item Price Missing");
        }
        if (itemReqDTO.getItemCategoryId() == null) {
            return ResponseEntity.status(422).body("Item Category ID Missing");
        }
        Item item = new Item();
        item.setName(itemReqDTO.getName());
        item.setPrice(itemReqDTO.getPrice());
        item.setDescription(itemReqDTO.getDescription());
        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(itemReqDTO.getItemCategoryId());
        item.setItemCategory(itemCategory);
        itemService.createItem(item);
        return ResponseEntity.status(201).body("Item Added OK!");
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody ItemReqDTO itemReqDTO) {
        if (itemService.getItemById(id) == null) {
            return ResponseEntity.status(201).body("Item Not Found!");
        }
        if (itemReqDTO.getItemCategoryId() == null) {
            return ResponseEntity.status(201).body("Item Category Not Found!");
        }
        Item updatedItem = new Item();

        updatedItem.setName(itemReqDTO.getName());
        updatedItem.setDescription(itemReqDTO.getDescription());
        updatedItem.setPrice(itemReqDTO.getPrice());
        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(itemReqDTO.getItemCategoryId());
        updatedItem.setItemCategory(itemCategory);

        itemService.updateItem(id, updatedItem);
        return ResponseEntity.status(201).body("Updated Item!");
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        if (itemService.getItemById(id) == null) {
            return ResponseEntity.status(404).body("Item Not Found!");
        }
        itemService.deleteItem(id);
        return ResponseEntity.status(201).body("Item Deleted!");
    }
}
