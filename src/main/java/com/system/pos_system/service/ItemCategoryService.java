package com.system.pos_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.ItemCategory;

@Service
public interface ItemCategoryService {
    List<ItemCategory> getAllItemCategories();

    ItemCategory createItemCategory(ItemCategory itemCategory);

    ItemCategory getItemCategoryById(Long id);

    ItemCategory updateItemCategory(long id, ItemCategory itemCategory);

    void deleteItemCategory(Long id);
}
