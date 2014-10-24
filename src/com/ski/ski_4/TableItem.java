package com.ski.ski_4;

/**
 * Created by Alyosha on 20.10.2014.
 */
public class TableItem {
    //private variables
    /*int _id;*/
    String _name;
    int _phone;
    int _price;
    String _date1;
    String _date2;

    // Empty constructor
    public TableItem(){

    }
    // constructor
    public TableItem(String name, int phone, int price, String _date1, String _date2){
        /*this._id = id;*/
        this._name = name;
        this._phone = phone;
        this._price = price;
        this._date1 =_date1;
        this._date2 = _date2;

    }

    /*// constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }*/

    /*// getting ID
    public int getID(){
        return this._id;
    }*/

    /*// setting id
    public void setID(int id){
        this._id = id;
    }*/

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public int getPhone(){
        return this._phone;
    }

    // setting phone number
    public void setPhone(int phone){
        this._phone = phone;
    }

    // getting price
    public int getPrice(){
        return this._price;
    }

    // setting price
    public void setPrice(int price){
        this._price = price;
    }

    // getting date1
    public String getDate1(){
        return this._date1;
    }

    // setting phone number
    public void setDate1(String date1){
        this._date1 = date1;
    }

    // getting date1
    public String getDate2(){
        return this._date2;
    }

    // setting phone number
    public void setDate2(String date2){
        this._date2 = date2;
    }
}
