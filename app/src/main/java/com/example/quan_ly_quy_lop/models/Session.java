package com.example.quan_ly_quy_lop.models;


import java.util.Date;

public class Session {
    private   int _id;
    private String name;
    private Fund fund;
    private float def_money;
    private Date date_begin;
    private Date date_end;

    public  Session(){
        super();
    }

    public Session(String name, Fund fund, float def_money, Date date_begin, Date date_end) {
        this.fund = fund;
        this.name = name;
        this.def_money = def_money;
        this.date_begin = date_begin;
        this.date_end = date_end;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public float getDef_money() {
        return def_money;
    }

    public void setDef_money(float def_money) {
        this.def_money = def_money;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}
