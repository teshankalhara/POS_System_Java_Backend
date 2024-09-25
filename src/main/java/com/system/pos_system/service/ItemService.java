package com.system.pos_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.Item;

@Service
public interface ItemService {
    List<Item> getAllItem();

    Item createItem(Item item);

    Item getItemById(Long id);

    Item updateItem(Long id, Item item);

    void deleteItem(Long id);
}
