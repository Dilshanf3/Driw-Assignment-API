package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceCalculationResponse {
    private Double price = 0.0;
    private Long itemId=(long) 0;
    private String itemName="";
    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }
    @JsonProperty("itemId")
    public Long getId() {
        return  itemId;
    }
    @JsonProperty("itemName")
    public String getItemName() {
        return  itemName;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setItemId(Long itemId2) {
        this.itemId = itemId2;
    }
    public void setItemName(String itemName2) {
        this.itemName = itemName2;
    }
}


