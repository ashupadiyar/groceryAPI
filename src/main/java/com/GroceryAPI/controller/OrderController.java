package com.GroceryAPI.controller;

import com.GroceryAPI.model.GroceryItem;
import com.GroceryAPI.services.GroceryItemService;
import com.GroceryAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping("/available-items")
    public ResponseEntity<List<GroceryItem>> getAvailableItems() {
        try {
            List<GroceryItem> availableItems = groceryItemService.getAllItems();
            return new ResponseEntity<>(availableItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/place-order")
    public ResponseEntity<List<GroceryItem>> placeOrder(@RequestBody List<Long> itemIds) {
        try {
            List<GroceryItem> orderedItems = orderService.orderItems(itemIds);
            return new ResponseEntity<>(orderedItems, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}