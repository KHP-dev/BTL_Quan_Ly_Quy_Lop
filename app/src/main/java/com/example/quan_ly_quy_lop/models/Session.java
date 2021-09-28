package com.example.quan_ly_quy_lop.models;

import java.sql.Date;

public class Session {
    int _id;
    Fund fund;
    Float def_money;
    Date date_begin;
    Date date_end;

    public  Session(){
        super();
    }

    public Session(int _id, Fund fund, Float def_money, Date date_begin, Date date_end) {
        this._id = _id;
        this.fund = fund;
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

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Float getDef_money() {
        return def_money;
    }

    public void setDef_money(Float def_money) {
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
