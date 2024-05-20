package com.jaime.helpers;

public class ResponseItem {
    private Float itemTotal;
    private Float itemDiscount;

    public ResponseItem() {
    }

    public ResponseItem(Float itemTotal, Float itemDiscount) {
        this.itemTotal = itemTotal;
        this.itemDiscount = itemDiscount;
    }

    public Float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Float itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Float getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(Float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }
}
