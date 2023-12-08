package com.GroceryAPI.services;

import com.GroceryAPI.model.GroceryItem;

import java.util.List;

public interface OrderService {
    List<GroceryItem> orderItems(List<Long> itemIds);
    List<GroceryItem> viewOrder(Long orderId);

}
