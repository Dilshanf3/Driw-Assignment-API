package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name = "assessmentData")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "cartoon_units", nullable = false)
    private Integer noOfUnitsInCartoon;

    @Column(name = "cartoon_price", nullable = false)
    private Double priceOFSingleCartoon;


    @Column(name = "adding_precentage")
    private Double increasedPrecentage = 0.3;

    @Column(name = "discount_precentage")
    private Double discountPrecentage = 0.1;

    @Column(name = "discount_margin")
    private Integer minCartoonAmountToDiscount = 3;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getMinCartoonAmountToDiscount() {
        return minCartoonAmountToDiscount;
    }

    public void setMinCartoonAmountToDiscount(Integer minCartoonAmountToDiscount) {
        this.minCartoonAmountToDiscount = minCartoonAmountToDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfUnitsInCartoon() {
        return noOfUnitsInCartoon;
    }

    public void setNoOfUnitsInCartoon(Integer noOfUnitsInCartoon) {
        this.noOfUnitsInCartoon = noOfUnitsInCartoon;
    }

    public Double getPriceOFSingleCartoon() {
        return priceOFSingleCartoon;
    }

    public void setPriceOFSingleCartoon(Double priceOFSingleCartoon) {
        this.priceOFSingleCartoon = priceOFSingleCartoon;
    }



    public Double getIncreasedPrecentage() {
        return increasedPrecentage;
    }

    public void setIncreasedPrecentage(Double increasedPrecentage) {
        this.increasedPrecentage = increasedPrecentage;
    }

    public Double getDiscountPrecentage() {
        return discountPrecentage;
    }

    public void setDiscountPrecentage(Double discountPrecentage) {
        this.discountPrecentage = discountPrecentage;
    }
   
    public Double calculateSingleUnitPrice() {
        return (this.priceOFSingleCartoon / this.noOfUnitsInCartoon) + (this.priceOFSingleCartoon / this.noOfUnitsInCartoon * this.increasedPrecentage);
    }
}
