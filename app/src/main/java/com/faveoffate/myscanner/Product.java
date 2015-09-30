package com.faveoffate.myscanner;

public class Product {

    //private variables
    int _id;
    String barcode;
    String product;

    // Empty constructor
    public Product(){

    }
    // constructor
    public Product(int id, String barcode, String product){
        this._id = id;
        this.barcode = barcode;
        this.product = product;
    }

    // constructor
    public Product(String barcode, String product){
        this.barcode = barcode;
        this.product = product;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getBarcode(){
        return this.barcode;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public String getProduct(){
        return this.product;
    }

    public void setProduct(String product){
        this.product = product;
    }
}