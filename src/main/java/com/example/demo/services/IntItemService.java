package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Item;
import com.example.demo.exception.ResourceNotFoundException;
public interface IntItemService extends PriceCalculations{
    List<Item> getAllItems();
    Item getItemById(Long id) throws ResourceNotFoundException;
    Item createOrUpdateItem(Item entity) throws ResourceNotFoundException;
}
