package com.GroceryAPI.services;

import com.GroceryAPI.model.GroceryItem;
import com.GroceryAPI.model.Order;
import com.GroceryAPI.repositories.GroceryItemRepository;
import com.GroceryAPI.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public List<GroceryItem> orderItems(List<Long> itemIds) {
        try {
            List<GroceryItem> orderedItems = groceryItemRepository.findAllById(itemIds);
            if (orderedItems.isEmpty()) {
                throw new EntityNotFoundException("No grocery items found for the provided IDs");
            }

            Order order = new Order();
            order.setItems(orderedItems);
            updateInventoryLevels(orderedItems);
            orderRepository.save(order);

            return orderedItems;
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while placing an order", e);
        }
    }

    @Override
    public List<GroceryItem> viewOrder(Long orderId) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()) {
                return optionalOrder.get().getItems();
            } else {
                throw new EntityNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while viewing an order", e);
        }
    }

    private void updateInventoryLevels(List<GroceryItem> orderedItems) {
        for (GroceryItem item : orderedItems) {
            int updatedQuantity = item.getQuantityInStock() - 1;
            item.setQuantityInStock(updatedQuantity);
            groceryItemRepository.save(item);
        }
    }

}
