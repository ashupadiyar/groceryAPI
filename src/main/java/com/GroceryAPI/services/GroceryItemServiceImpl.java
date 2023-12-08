package com.GroceryAPI.services;

import com.GroceryAPI.model.GroceryItem;
import com.GroceryAPI.repositories.GroceryItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {


    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public List<GroceryItem> getAllItems() {
        try {
            return groceryItemRepository.findAll();
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while retrieving grocery items", e);
        }
    }

    @Override
    public GroceryItem addItem(GroceryItem item) {
        try {
            return groceryItemRepository.save(item);
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while adding a grocery item", e);
        }
    }

    @Override
    public void removeItem(Long itemId) {
        try {
            groceryItemRepository.deleteById(itemId);
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while removing a grocery item", e);
        }
    }

    @Override
    public void updateItem(Long itemId, GroceryItem updatedItem) {
        try {
            if (groceryItemRepository.existsById(itemId)) {
                updatedItem.setId(itemId);
                groceryItemRepository.save(updatedItem);
            } else {
                throw new EntityNotFoundException("Grocery item with ID " + itemId + " not found");
            }
        } catch (Exception e) {
            // You might log the exception for debugging purposes
            throw new ServiceException("Error while updating a grocery item", e);
        }
    }
}

