package com.example.quan_ly_quy_lop.models;

import java.sql.Date;

public class Expense {
    private int _id;
    private Fund fund;
    private String title;
    private String des;
    private Float price;
    private Date date_create;

    public Expense(){
        super();
    }

    public Expense( Fund fund, String title, String des, Float price, Date date_create) {
        this.fund = fund;
        this.title = title;
        this.des = des;
        this.price = price;
        this.date_create = date_create;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }
}
