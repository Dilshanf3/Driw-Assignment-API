package com.example.demo.controller;

import com.example.demo.dtos.PriceCalculation;
import com.example.demo.dtos.PriceCalculationResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.model.TotalPrice;
import com.example.demo.services.IntItemService;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    IntItemService service;

    //Return all the items that available in system
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }
   //return specific item that matched with the id
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Item entity = service.getItemById(id);
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    //  Create / update item
    @PostMapping
    public ResponseEntity<Item> createOrUpdateItem(@RequestBody Item item) throws ResourceNotFoundException {
        Item updated = service.createOrUpdateItem(item);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }

   //Calculate price of a single item purchased
    @GetMapping("/calculate_price/single/{itemName}/{itemId}/{amount}")
    public ResponseEntity<PriceCalculationResponse> calculateSingleItemPrice(@PathVariable("itemName") String itemName,@PathVariable("itemId") Long itemId, @PathVariable("amount") Integer amount) throws ResourceNotFoundException {
        Item item = service.getItemById(itemId);
        PriceCalculationResponse dto = new PriceCalculationResponse();
        if(item != null) {
            dto.setPrice(service.calculateItemPrice(item, amount));
            dto.setItemId(itemId);
            dto.setItemName(itemName);
           
            
        }
        return new ResponseEntity<PriceCalculationResponse>(dto, new HttpHeaders(), HttpStatus.OK);
    }

   
     //Calculate price if many items are purchased
    @PostMapping("/calculate_price/all")
    public ResponseEntity<PriceCalculationResponse> calculateTotalPrice(@RequestBody ArrayList<PriceCalculation> itemsList) throws ResourceNotFoundException {
        List<TotalPrice> items = new ArrayList<TotalPrice>();
        for(PriceCalculation item: itemsList) {
            Item availableItem = service.getItemById(item.getItemId());
            if(availableItem != null) {
                TotalPrice newPrice = new TotalPrice();
                newPrice.setItem(availableItem);
                newPrice.setPurchasedAmount(item.getAmount());
                items.add(newPrice);
            }
        }
        PriceCalculationResponse dto = new PriceCalculationResponse();
        dto.setPrice(service.calculateTotalPrice(items));
        return new ResponseEntity<PriceCalculationResponse>(dto, new HttpHeaders(), HttpStatus.OK);
    }

}

