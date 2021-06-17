package com.example.covid_19application.ui.shop;

public class Mask {
    private Integer snum;
    private String product;
    private String kind;
    private String price;
    private int quantity;

    public Mask(){}

    public Mask(String product, String kind, String price, int quantity) {
        this.product = product;
        this.kind = kind;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}