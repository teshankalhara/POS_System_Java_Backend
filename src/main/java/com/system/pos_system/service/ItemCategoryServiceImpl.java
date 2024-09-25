package com.system.pos_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.pos_system.entity.ItemCategory;
import com.system.pos_system.repository.ItemCategoryRepository;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryRepository.findAll();
    }

    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory) {
        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public ItemCategory updateItemCategory(long id, ItemCategory itemCategory) {
        ItemCategory existingItemCategory = itemCategoryRepository.findById(id).orElse(null);
        if (existingItemCategory != null) {
            existingItemCategory.setName(itemCategory.getName());
            return existingItemCategory;
        }
        return null;
    }

    @Override
    public void deleteItemCategory(Long id) {
        itemCategoryRepository.deleteById(id);
    }

}
