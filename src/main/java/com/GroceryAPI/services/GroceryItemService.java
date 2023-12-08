package com.GroceryAPI.services;

import com.GroceryAPI.model.GroceryItem;

import java.util.List;

public interface GroceryItemService {
    List<GroceryItem> getAllItems();
    GroceryItem addItem(GroceryItem item);
    void removeItem(Long itemId);
    void updateItem(Long itemId, GroceryItem updatedItem);
}
