package com.example.demo.services;

import com.example.demo.model.*;


import java.util.List;

public interface PriceCalculations {
    Double calculateItemPrice(Item item, Integer amount);
    Double calculateTotalPrice(List<TotalPrice> list);
}
