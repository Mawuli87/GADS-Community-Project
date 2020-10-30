package com.messieyawo.salesapp.order;

public class orderItem {
    private int id;
    private String name;
    private String price;
    private int qty;
    private String mobile;


    public orderItem(int id, String name, String price, int qty, String mobile){

        this.id= id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.mobile = mobile;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
